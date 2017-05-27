package brasileirao.api.dao;

import brasileirao.api.persistence.Club;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Classe de acesso aos dados da entidade <i>Club</i>.
 */
@Repository
public interface ClubDao extends CrudRepository<Club, Long> {

   /**
    * Busca por um clube usando como critério seu identificador.
    *
    * @param id Identificador usado como critério de busca.
    * @return Instância do clube encontrado. <i>null</i> caso o clube não seja encontrado.
    */
   Club findById(Long id);
}
