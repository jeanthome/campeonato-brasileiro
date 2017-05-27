package brasileirao.api.service;

import brasileirao.api.persistence.Player;

/**
 * Classe de serviços da entidade <i>Player</i>.
 */
public interface PlayerService {

   /**
    * Busca um jogador usando como critério seu nome de exibição.
    *
    * @param displayName Nome de exibição do jogador a ser buscado.
    * @return Instância do jogador encontrado, <i>null</i> caso não encontre.
    */
   Player findByDisplayName(String displayName);

   /**
    * Busca por um jogador usando como critério seu identificador.
    *
    * @param id Identificador a ser usado como critério de busca.
    * @return Instância do jogador encontrado, <i>null</i> caso não encontre.
    */
   Player findById(Long id);

   /**
    * Persiste no banco uma instância da entidade <i>Player</i>
    *
    * @param player Instância a ser persistida.
    * @return Instância persistida
    * @see Player
    */
   Player save(Player player);

   /**
    * Retorna lista de todos os jogadores existentes no banco.
    *
    * @return Iterable com a lista de jogadores.
    */
   Iterable<Player> findAll();
}
