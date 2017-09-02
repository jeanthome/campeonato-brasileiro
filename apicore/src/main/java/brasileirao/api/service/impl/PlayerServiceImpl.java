package brasileirao.api.service.impl;

import brasileirao.api.dao.PlayerDao;
import brasileirao.api.dto.PlayerDto;
import brasileirao.api.dto.PlayerMinDto;
import brasileirao.api.dto.PlayerRegisterDto;
import brasileirao.api.helper.DateHelper;
import brasileirao.api.persistence.Club;
import brasileirao.api.persistence.Player;
import brasileirao.api.service.ClubService;
import brasileirao.api.service.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

   /**
    * Retorna lista de todos os jogadores existentes no banco.
    *
    * @return Iterable com a lista de jogadores.
    */
   @Override
   public Iterable<Player> findAll() {
      return playerDao.findAll();
   }

   @Override
   public Player findById(Long id) {
      return playerDao.findById(id);
   }

   /**
    * Converte uma instância de {@link Player} ao seu respectivo DTO.
    *
    * @param player Instância da classe {@link Player}, que será convertida em DTO.
    * @return Instância de {@link PlayerDto}
    */
   public PlayerDto convertPlayerToDto(Player player) {
      final ModelMapper modelMapper = new ModelMapper();
      final PlayerDto playerDto = modelMapper.map(player, PlayerDto.class);

      final Map<String, String> position = new HashMap<>();
      position.put("name", player.getPositionEnum().getPositionName());
      position.put("abbreviation", player.getPositionEnum().getAbbreviation());
      playerDto.setPosition(position);
      playerDto.setIdentificator(player.getId());

      /*Obtem e setta a idade do jogador*/
      final Date date = player.getBirthDate();
      playerDto.setAge(DateHelper.convertDateToPeriod(date).getYears());

      return playerDto;
   }

   /**
    * Converte uma instância de {@link Player} em seu DTO de informações mínimas.
    *
    * @param player Instância da classe {@link Player}, que será convertida em DTO.
    * @return Instância de {@link PlayerMinDto}
    */
   public PlayerMinDto convertPlayerToMinDto(Player player) {
      final ModelMapper modelMapper = new ModelMapper();
      final PlayerMinDto playerMinDto = modelMapper.map(player, PlayerMinDto.class);
      playerMinDto.setPositionAbbreviation(player.getPositionEnum().getAbbreviation());
      return playerMinDto;
   }

   public List<PlayerMinDto> convertPlayerListToPlayerMinDtoList(List<Player> playerList) {

      final List<PlayerMinDto> playerMinDtoList = new ArrayList<>();
      for (Player player : playerList ) {
         final PlayerMinDto playerMinDto = this.convertPlayerToMinDto(player);
         playerMinDtoList.add( playerMinDto);
      }
      return playerMinDtoList;
   }


   /**
    * Converte DTO da entidade Player em seu respectivo objeto.
    *
    * @param playerRegisterDto DTO com os dados.
    * @return Instancia de {@link Player}.
    */
   @Override
   public Player convertPlayerRegisterDtoToPlayer(PlayerRegisterDto playerRegisterDto) {

      final ModelMapper modelMapper = new ModelMapper();
      final Player player = modelMapper.map(playerRegisterDto, Player.class);

      if (playerRegisterDto.getActualClubId() != null) {
         final Club club = this.clubService.findById(playerRegisterDto.getActualClubId());
         player.setActualClub(club);
      }

      if (playerRegisterDto.getNationality().length() < 3) {
         player.setNationality("Brasileiro");
      }
      return player;
   }
}
