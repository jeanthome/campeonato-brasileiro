package brasileirao.api.service.impl;

import brasileirao.api.dao.PlayerDao;
import brasileirao.api.dao.PlayerInMatchDao;
import brasileirao.api.dto.PlayerMinDto;
import brasileirao.api.persistence.Player;
import brasileirao.api.persistence.PlayerInMatch;
import brasileirao.api.service.PlayerInMatchService;
import brasileirao.api.service.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementação da classe de serviços da entidade PlayerInMatch.
 */
@Service
public class PlayerInMatchServiceImpl implements PlayerInMatchService {

  /**
   * Classe de acesso de dados da entidade PlayerInMatch
   */
  @Autowired
  private PlayerInMatchDao playerInMatchDao;

  /**
   * Instância da classe de serviços da entidade {@link brasileirao.api.persistence.Player}
   */
  @Autowired
  private PlayerService playerService;

  /**
   * Objeto de acesso de dados da entidade {@link Player};
   */
  @Autowired
  private PlayerDao playerDao;

  @Override
  public PlayerInMatch save(PlayerInMatch playerInMatch) {
    return playerInMatchDao.save(playerInMatch);
  }

  @Override
  public List<PlayerInMatch> getPlayerListByIdList(List<Long> idList) {

    final List<PlayerInMatch> playerInMatchList = new ArrayList<>();

    for (Long id : idList) {
      final Player player = this.playerDao.findById(id);

      if (player != null) {
        final PlayerInMatch playerInMatch = this.convertPlayerToPlayerInMatch(player);
        playerInMatchList.add(playerInMatch);
      }
    }
    return playerInMatchList;
  }

  @Override
  public PlayerInMatch convertPlayerToPlayerInMatch(Player player) {
    final PlayerInMatch playerInMatch = new PlayerInMatch();
    playerInMatch.setDisplayName(player.getDisplayName());
    playerInMatch.setNumber(player.getNumber());
    playerInMatch.setPositionEnum(player.getPositionEnum());
    playerInMatch.setSourcePlayer(player);
    this.save(playerInMatch);
    return playerInMatch;
  }

  @Override
  public PlayerMinDto convertPlayerInMatchToMinDto(PlayerInMatch playerInMatch) {
    final ModelMapper modelMapper = new ModelMapper();
    final PlayerMinDto playerMinDto = modelMapper.map(playerInMatch, PlayerMinDto.class);
    playerMinDto.setPositionAbbreviation(playerInMatch.getPositionEnum().getAbbreviation());
    playerMinDto.setId(playerInMatch.getId());
    return playerMinDto;
  }

  @Override
  public List<PlayerMinDto> convertPlayerInMatchListToPlayerMinDtoList(
      List<PlayerInMatch> playerList) {

    final List<PlayerMinDto> playerMinDtoList = new ArrayList<>();
    Collections.sort(playerList);
    for (PlayerInMatch player : playerList) {
      final PlayerMinDto playerMinDto = this.convertPlayerInMatchToMinDto(player);
      playerMinDtoList.add(playerMinDto);
    }
    return playerMinDtoList;
  }
}
