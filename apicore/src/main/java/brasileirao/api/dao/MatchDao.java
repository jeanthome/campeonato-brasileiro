package brasileirao.api.dao;

import brasileirao.api.persistence.Match;
import org.springframework.data.repository.CrudRepository;

/**
 * Classe de acesso aos dados da entidade {@link Match}.
 */
public interface MatchDao extends CrudRepository<Match, Long> {
}
