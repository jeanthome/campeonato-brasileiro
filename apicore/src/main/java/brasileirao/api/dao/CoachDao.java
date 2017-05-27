package brasileirao.api.dao;

import brasileirao.api.persistence.Club;
import brasileirao.api.persistence.Coach;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Classe de acesso aos dados da entidade <i>Coach</i>.
 */
@Repository
public interface CoachDao extends CrudRepository<Coach, Long> {

   /**
    * Busca por um técnico usando como critério seu identificador.
    *
    * @param id Identificador usado como critério de busca.
    * @return Instância do técnico encontrado. <i>null</i> caso o técnico não seja encontrado.
    */
   Coach findById(Long id);

   /**
    * Busca por um ténico usando como critério seu clube atual.
    *
    * @param club Instância da classe <i>Club</i> a ser usada como critério.
    * @return Instância do técnico encontrado. <i>null</i> caso o técnico não seja encontrado.
    */
   Coach findByActualClub(Club club);

}
