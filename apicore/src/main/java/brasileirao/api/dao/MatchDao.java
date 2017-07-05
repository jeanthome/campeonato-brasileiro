package brasileirao.api.dao;

import brasileirao.api.persistence.Match;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Classe de acesso aos dados da entidade {@link Match}.
 */
public interface MatchDao extends CrudRepository<Match, Long> {

   /**
    * Busca no banco as partidas de uma dada rodada, ordenadas crescentemente pela data.
    * @param roundNumber Número da rodada.
    * @return Lista das partidas encontradas.
    */
   List<Match> findByRoundNumberOrderByKickOffAsc(Long roundNumber);

   /**
    * Busca uma partida usando como critério o seu identifador.
    *
    * @param matchId Identificador da partida
    * @return Instância de {@link Match} se existe. null, caso contrário.
    */
   Match findById(Long matchId);
}
