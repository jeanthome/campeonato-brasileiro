package brasileirao.api.service;

import brasileirao.api.dto.ClubDto;
import brasileirao.api.persistence.Club;

/**
 * Classe de serviços da entidade <i>Club</i>.
 */
public interface ClubService {

   /***
    * Busca por um clube usando como critério seu identificador.
    *
    * @param id Identificador usado como critério de busca.
    * @return Instância do clube encontrado. Null caso o clube não seja encontrado.
    */
   Club findById(Long id);

   /**
    * Salva no banco uma instância da entidade <i>Club</i>.
    *
    * @param club Instância a ser salva no banco.
    * @return Instância salva.
    */
   Club save(Club club);

   /**
    * Retorna lista de todos os clubes existentes no banco.
    *
    * @return Iterable com a lista de clubes.
    */
   Iterable<Club> findAll();

   /**
    * Converte uma instância de <i>Club</i> ao seu respectivo DTO.
    *
    * @param club Instância da classe <i>Club</i>, que será convertida em DTO.
    * @return Instância de <i>ClubDto</i>
    */
   ClubDto convertClubToDto(Club club);


}
