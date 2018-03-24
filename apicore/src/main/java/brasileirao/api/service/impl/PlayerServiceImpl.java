package brasileirao.api.service.impl;

import brasileirao.api.dao.PlayerDao;
import brasileirao.api.dto.PlayerDto;
import brasileirao.api.dto.PlayerMinDto;
import brasileirao.api.dto.PlayerRegisterDto;
import brasileirao.api.enums.ServiceExceptionMessageEnum;
import brasileirao.api.exception.ServiceException;
import brasileirao.api.exception.ValidationException;
import brasileirao.api.helper.DateHelper;
import brasileirao.api.persistence.Club;
import brasileirao.api.persistence.Player;
import brasileirao.api.service.ClubService;
import brasileirao.api.service.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Implementação da classe de serviços da entidade <i>Player</i>.
 *
 * @see Player
 */
@Service
public class PlayerServiceImpl implements PlayerService {

  /**
   * Objeto de acesso de dados da classe <i>Player</i>.
   */
  @Autowired
  private PlayerDao playerDao;

  /**
   * Classe de serviços da entidade {@link Club}
   * */
  @Autowired
  private ClubService clubService;

  @Override
  public Player findByDisplayName(String displayName) {
    return playerDao.findByDisplayName(displayName);
  }

  @Override
  public Player save(Player player) {
    return playerDao.save(player);
  }

  @Override
  public Iterable<Player> findAll() {
    return playerDao.findAll();
  }

  @Override
  public PlayerDto getPlayerById(Long playerId) throws ServiceException, ValidationException, IOException {

    final Player player = this.playerDao.findById(playerId);

    PlayerDto playerDto = null;

    if (player != null) {
      playerDto = this.convertPlayerToDto(player);
      playerDto.addLinks(player);
    }

    return playerDto;
  }

  public PlayerDto convertPlayerToDto(Player player) {
    final ModelMapper modelMapper = new ModelMapper();
    final PlayerDto playerDto = modelMapper.map(player, PlayerDto.class);

    final Map<String, String> position = new HashMap<>();
    position.put("name", player.getPositionEnum().getPositionName());
    position.put("abbreviation", player.getPositionEnum().getAbbreviation());
    playerDto.setPosition(position);
    playerDto.setIdentifier(player.getId());

    /* Obtem e setta a idade do jogador */
    final Date date = player.getBirthDate();
    playerDto.setAge(DateHelper.convertDateToPeriod(date).getYears());

    return playerDto;
  }


  public PlayerMinDto convertPlayerToMinDto(Player player) {
    final ModelMapper modelMapper = new ModelMapper();
    final PlayerMinDto playerMinDto = modelMapper.map(player, PlayerMinDto.class);
    playerMinDto.setPositionAbbreviation(player.getPositionEnum().getAbbreviation());
    return playerMinDto;
  }

  public List<PlayerMinDto> convertPlayerListToPlayerMinDtoList(List<Player> playerList) {

    final List<PlayerMinDto> playerMinDtoList = new ArrayList<>();
    for (Player player : playerList) {
      final PlayerMinDto playerMinDto = this.convertPlayerToMinDto(player);
      playerMinDtoList.add(playerMinDto);
    }
    return playerMinDtoList;
  }

  @Override
  public Player convertPlayerRegisterDtoToPlayer(PlayerRegisterDto playerRegisterDto) {

    final ModelMapper modelMapper = new ModelMapper();
    final Player player = modelMapper.map(playerRegisterDto, Player.class);

    if (playerRegisterDto.getActualClubId() != null) {
      final Club club = this.clubService.findById(playerRegisterDto.getActualClubId());
      player.setActualClub(club);
    }

    return player;
  }

  @Override
  public List<PlayerDto> getAllPlayers() throws IOException, ValidationException, ServiceException {

    final Iterable<Player> playerIterable = this.playerDao.findAll();
    final Iterator<Player> playerIterator = playerIterable.iterator();
    final List<PlayerDto> playerDtoList = new ArrayList<>();

    while (playerIterator.hasNext()) {
      final Player player = playerIterator.next();
      final PlayerDto playerDto = this.convertPlayerToDto(player);

      playerDto.addLinks(player);
      playerDtoList.add(playerDto);
    }
    return playerDtoList;
  }

  @Override
  public ResponseEntity getPlayerImage(Long playerId) throws ServiceException, IOException {

    final Player player = this.playerDao.findById(playerId);

    if (player == null) {
      throw new ServiceException(ServiceExceptionMessageEnum.PLAYER_NOT_FOUND.getMessage());
    }

    final ClassPathResource image =
        new ClassPathResource("/images/clubs/" + player.getActualClub().getFolderName() + "/"
            + player.getPhoto() + ".png");

    final InputStreamResource inputStreamResource;
    try {
      inputStreamResource = new InputStreamResource(image.getInputStream());
    } catch (IOException e) {
      throw new ServiceException(ServiceExceptionMessageEnum.PLAYER_IMAGE_NOT_FOUND.getMessage());
    }

    return ResponseEntity.ok().contentLength(image.contentLength())
        .contentType(MediaType.IMAGE_PNG).body(inputStreamResource);
  }
}
