package brasileirao.api.service.impl;

import brasileirao.api.converter.ConvertHelper;
import brasileirao.api.dao.PlayerDao;
import brasileirao.api.dto.PlayerDto;
import brasileirao.api.persistence.Player;
import brasileirao.api.service.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementação da classe de serviços da entidade <i>Player</i>.
 * @see Player
 */
@Service
public class PlayerServiceImpl implements PlayerService {

   /**
    * Objeto de acesso de dados da classe <i>Player</i>.
    */
   @Autowired
   private PlayerDao playerDao;

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
   public  PlayerDto convertPlayerToDto(Player player) {
      final ModelMapper modelMapper = new ModelMapper();
      final PlayerDto playerDto = modelMapper.map(player, PlayerDto.class);

      final Map<String, String> position = new HashMap<>();
      position.put("name", player.getPositionEnum().getPositionName());
      position.put("abbreviation", player.getPositionEnum().getAbbreviation());
      playerDto.setPosition(position);

      /*Obtem e setta a idade do jogador*/
      final Date date = player.getBirthDate();
      playerDto.setAge(ConvertHelper.convertDateToPeriod(date).getYears());

      return playerDto;
   }
}
