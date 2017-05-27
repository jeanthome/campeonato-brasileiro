package brasileirao.api.dao;

import brasileirao.api.persistence.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Classe de acesso aos dados da entidade <i>Player</i>.
 */
@Repository
public interface PlayerDao extends CrudRepository<Player, Long> {

   /**
    * Busca por um jogador usando como critério seu nome de exibição.
    *
    * @param displayName Nome de exibição a ser usado como critério de busca.
    * @return Instância do jogador encontrado. <i>null</i> caso o jogador não seja encontrado.
    */
   Player findByDisplayName(String displayName);

   /**
    * Busca por um jogador usando como critério seu identificador.
    *
    * @param id Identificador a ser usado como critério de busca.
    * @return Instância do jogador encontrado. <i>null</i> caso o jogador não seja encontrado.
    */
   Player findById(Long id);

}
