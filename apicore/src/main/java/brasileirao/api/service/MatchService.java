package brasileirao.api.service;

import brasileirao.api.persistence.Match;

/**
 * Classe de serviços da entidade {@link Match}.
 */
public interface MatchService {

   /**
    * Persiste no banco uma instância da entidade {@link Match}
    *
    * @param match Instância a ser persistida.
    * @return Instância persistida
    * @see Match
    */
   Match save(Match match);
}
