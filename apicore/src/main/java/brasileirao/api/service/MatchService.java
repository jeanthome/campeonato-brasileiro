package brasileirao.api.service;

import brasileirao.api.dto.ClubDto;
import brasileirao.api.dto.MatchDto;
import brasileirao.api.persistence.Club;
import brasileirao.api.persistence.Match;

import java.util.List;

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

   /**
    * Retorna as partidas de uma dada rodada.
    * @param roundNumber O número da rodada.
    * @return List com as partidas encontradas.
    */
   List<Match> getMatchesInRound(Long roundNumber);

   /**
    * Busca uma partida usando como critério o seu identifador.
    *
    * @param matchId Identificador da partida
    * @return Instância de {@link Match} se existe. null, caso contrário.
    */
   Match findById(Long matchId);

   /**
    * Converte uma instância de <i>Match</i> ao seu respectivo DTO.
    *
    * @param match Instância da classe <i>Club</i>, que será convertida em DTO.
    * @return Instância de <i>ClubDto</i>
    */
   MatchDto convertMatchToDto(Match match);
}