package brasileirao.api.dao;

import brasileirao.api.persistence.Substitution;
import org.springframework.data.repository.CrudRepository;

/**
 * Objeto de acesso de dados da entidade {@link Substitution}.
 */
public interface SubstitutionDao extends CrudRepository<Substitution, Long> {
}
