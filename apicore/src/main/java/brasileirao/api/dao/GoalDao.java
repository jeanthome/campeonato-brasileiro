package brasileirao.api.dao;

import brasileirao.api.persistence.Goal;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface de acesso de dados da entidade Goal.
 */
public interface GoalDao extends CrudRepository<Goal, Long> {

}
