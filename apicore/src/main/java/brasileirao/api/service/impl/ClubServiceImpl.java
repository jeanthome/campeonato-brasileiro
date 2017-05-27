package brasileirao.api.service.impl;

import brasileirao.api.dao.ClubDao;
import brasileirao.api.persistence.Club;
import brasileirao.api.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementação da classe de serviços da entidade <i>Club</i>.
 */
@Service
public class ClubServiceImpl implements ClubService {

   /**
    * Objeto de acesso de dados da classe <i>Club</i>.
    */
   @Autowired
   private ClubDao clubDao;


   /**
    * Busca por um clube usando como critério seu identificador.
    *
    * @param id Identificador usado como critério de busca.
    * @return Instância do clube encontrado. Null caso o clube não seja encontrado.
    */
   @Override
   public Club findById(Long id) {
      return clubDao.findById(id);
   }

   /**
    * Salva no banco uma instância da entidade 'Club'.
    *
    * @param club instância a ser salva no banco.
    * @return Instância salva.
    */
   @Override
   public Club save(Club club) {
      return clubDao.save(club);
   }

   /**
    * Retorna lista de todos os clubes existentes no banco.
    *
    * @return Iterable com a lista de clubes.
    */
   @Override
   public Iterable<Club> findAll() {
      return clubDao.findAll();
   }
}
