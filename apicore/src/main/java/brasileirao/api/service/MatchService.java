package brasileirao.api.service;

import brasileirao.api.dto.input.CardInputDto;
import brasileirao.api.dto.GoalDto;
import brasileirao.api.dto.input.GoalInputDto;
import brasileirao.api.dto.MatchDto;
import brasileirao.api.dto.input.MatchInputDto;
import brasileirao.api.dto.MatchMinDto;
import brasileirao.api.dto.input.SubstitutionInputDto;
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

   /**
    * Insere um gol em uma partida.
    *
    * @param goalInputDto Dto com as informações do gol.
    * @return Dto com os dados do gol inserido.
    * @throws ServiceException Exceção que pode ser lançada ao não encontrar uma entidade.
    */
   GoalDto insertGoalInMatch(GoalInputDto goalInputDto) throws ServiceException;

   /**
    * Insere um cartão em uma partida.
    *
    * @param cardInputDto Dto com as informações do cartão a ser inserido,
    * @throws ServiceException Exceção que pode ser lançada ao não encontrar uma entidade.
    */
   void insertCardInMatch(CardInputDto cardInputDto) throws ServiceException;

   /**
    * Insere uma substituição em uma partida.
    *
    * @param substitutionInputDto Dto com as informações da substituição a ser inserida.
    * @throws ServiceException
    */
   void insertSubstitutionInMatch(SubstitutionInputDto substitutionInputDto) throws ServiceException;
   /**
    * @param matchInputDto Dto com as informações da partida a ser inserida.
    * @throws ServiceException Exceção caso não sejam encontrados os times.
    * @throws ParseException   Excecão que pode ser lançada ao converter a data.
    */
   void insertMatch(MatchInputDto matchInputDto) throws ServiceException, ParseException;

}
