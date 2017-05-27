package brasileirao.api.service.impl;

import brasileirao.api.dao.PlayerDao;
import brasileirao.api.persistence.Player;
import brasileirao.api.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
