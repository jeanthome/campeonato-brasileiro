package brasileirao.api.service;

import brasileirao.api.dto.GoalInputDto;
import brasileirao.api.dto.MatchDto;
import brasileirao.api.dto.MatchInputDto;
import brasileirao.api.dto.MatchMinDto;
import brasileirao.api.exception.ServiceException;
import brasileirao.api.persistence.Match;

import java.text.ParseException;
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
    *
    * @param roundNumber O número da rodada.
    * @return List com as partidas encontradas.
    */
   List<MatchMinDto> getMatchesInRound(Long roundNumber);

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

   void insertGoalInMatch(GoalInputDto goalInputDto) throws ServiceException;

   /**
    * @param matchInputDto Dto com as informações da partida a ser inserida.
    * @throws ServiceException Exceção caso não sejam encontrados os times.
    * @throws ParseException   Excecão que pode ser lançada ao converter a data.
    */
   void insertMatch(MatchInputDto matchInputDto) throws ServiceException, ParseException;
}
