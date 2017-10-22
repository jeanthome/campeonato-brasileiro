package brasileirao.api.dao;

import brasileirao.api.persistence.Card;
import org.springframework.data.repository.CrudRepository;

/**
 * Classe de acesso aos dados da entidade {@link Card}.
 */
public interface CardDao extends CrudRepository<Card, Long> {
}
