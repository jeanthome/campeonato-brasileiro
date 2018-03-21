package brasileirao.api.service.impl;

import brasileirao.api.dao.ClubDao;
import brasileirao.api.dto.ClubDto;
import brasileirao.api.dto.CoachDto;
import brasileirao.api.dto.PlayerDto;
import brasileirao.api.enums.ServiceExceptionMessageEnum;
import brasileirao.api.exception.ServiceException;
import brasileirao.api.exception.ValidationException;
import brasileirao.api.persistence.Club;
import brasileirao.api.persistence.Coach;
import brasileirao.api.persistence.Player;
import brasileirao.api.service.ClubService;
import brasileirao.api.service.CoachService;
import brasileirao.api.service.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Implementação da classe de serviços da entidade <i>Club</i>.
 */
@Service
public class ClubServiceImpl implements ClubService {

  /**
   * Objeto de acesso de dados da classe <i>Club</i>.
   */
  @Autowired
  private ClubDao clubDao;

  /**
   * Instância da classe de serviços da entidade <i>Coach</i>
   */
  @Autowired
  private CoachService coachService;


  /***
   * Instância da classe de serviços da entidade {@link Player}
   */
  @Autowired
  private PlayerService playerService;


  /**
   * Busca por um clube usando como critério seu identificador.
   *
   * @param id Identificador usado como critério de busca.
   * @return Instância do clube encontrado. Null caso o clube não seja encontrado.
   */
  @Override
  public Club findById(Long id) {
    return clubDao.findById(id);
  }

  /**
   * Salva no banco uma instância da entidade 'Club'.
   *
   * @param club instância a ser salva no banco.
   * @return Instância salva.
   */
  @Override
  public Club save(Club club) {
    return clubDao.save(club);
  }

  /**
   * Retorna lista de todos os clubes existentes no banco.
   *
   * @return Iterable com a lista de clubes.
   */
  @Override
  public Iterable<Club> findAll() {
    return clubDao.findAll();
  }

  /**
   * Converte uma instância de <i>Club</i> ao seu respectivo DTO.
   *
   * @param club Instância da classe <i>Club</i>, que será convertida em DTO.
   * @return Instância de <i>ClubDto</i>
   */
  @Override
  public ClubDto convertClubToClubDto(Club club) {
    final ModelMapper modelMapper = new ModelMapper();
    final ClubDto clubDto = modelMapper.map(club, ClubDto.class);
    clubDto.setIdentifier(club.getId());
    return clubDto;
  }

  @Override
  public List<ClubDto> getAllClubs() throws IOException, ValidationException, ServiceException {

    final Iterable<Club> clubIterable = this.findAll();
    final Iterator<Club> clubIterator = clubIterable.iterator();

    final List<ClubDto> clubDtoList = new ArrayList<>();
    while (clubIterator.hasNext()) {
      final Club club = clubIterator.next();
      final ClubDto clubDto = this.convertClubToClubDto(club);
      clubDto.addLinks(club.getId());
      clubDtoList.add(clubDto);
    }

    return clubDtoList;

  }

  @Override
  public ClubDto getClubById(Long clubId) throws IOException, ValidationException, ServiceException {

    ClubDto clubDto = null;
    final Club club = this.findById(clubId);
    if (club != null) {
      clubDto = this.convertClubToClubDto(club);
      clubDto.addLinks(club.getId());
    }
    return clubDto;
  }

  @Override
  public CoachDto getClubCoach(Long clubId) throws ServiceException, IOException,
      ValidationException {

    final Club club = this.findById(clubId);
    final CoachDto coachDto;

    if (club != null) {
      final Coach coach = this.coachService.findByActualClub(club);

      if (coach == null) {
        throw new ServiceException(ServiceExceptionMessageEnum.COACH_NOT_FOUND.getMessage());
      }
      coachDto = this.coachService.convertCoachToDto(coach);
      coachDto.addLinks(clubId);

    } else {
      throw new ServiceException(ServiceExceptionMessageEnum.CLUB_NOT_FOUND.getMessage());
    }
    return coachDto;
  }

  @Override
  public List<PlayerDto> getClubPlayers(Long clubId) throws ServiceException, IOException,
      ValidationException {

    final Club club = this.findById(clubId);
    final List<PlayerDto> playerDtoList = new ArrayList<>();

    if (club != null) {

      /* Ordena jogadores por posição */
      Collections.sort(club.getPlayerList());

      for (Player player : club.getPlayerList()) {
        final PlayerDto playerDto = this.playerService.convertPlayerToDto(player);
        playerDto.addLinksToPlayer(player.getId(), clubId);
        playerDtoList.add(playerDto);
      }

    } else {
      throw new ServiceException(ServiceExceptionMessageEnum.CLUB_NOT_FOUND.getMessage());
    }

    return playerDtoList;

  }
}
