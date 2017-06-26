package brasileirao.api.service.impl;

import brasileirao.api.dao.MatchDao;
import brasileirao.api.persistence.Match;
import brasileirao.api.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementação da classe de serviços da entidade {@link Match}.
 */
@Service
public class MatchServiceImpl implements MatchService {

   /**
    * Objeto de acesso de dados da classe {@link Match}.
    */
   @Autowired
   private MatchDao matchDao;

   @Override
   public Match save(Match match) {
      return this.matchDao.save(match);
   }
}
