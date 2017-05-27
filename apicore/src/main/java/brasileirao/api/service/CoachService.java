package brasileirao.api.service;

import brasileirao.api.persistence.Club;
import brasileirao.api.persistence.Coach;

/**
 * Classe de serviços da entidade <i>Coach</i>.
 */
public interface CoachService {

   /**
    * Busca por um técnico usando como critério seu identificador.
    *
    * @param id Identificador usado como critério de busca.
    * @return Instância do técnico encontrado. <i>null</i> caso o técnico não seja encontrado.
    */
   Coach findById(Long id);

   /**
    * Persiste no banco uma instância da entidade <i>Coach</i>
    *
    * @param coach Instância a ser persistida.
    * @return Instância persistida
    * @see Coach
    */
   Coach save(Coach coach);

   /**
    * Retorna lista de todos os técnicos existentes no banco.
    *
    * @return Iterable com a lista de técnicos.
    */
   Iterable<Coach> findAll();

   /**
    * Busca por um ténico usando como critério seu clube atual.
    *
    * @param club Instância da classe <i>Club</i> a ser usada como critério.
    * @return Instância do técnico encontrado. <i>null</i> caso o técnico não seja encontrado.
    */
   Coach findByActualClub(Club club);
}
