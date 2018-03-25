package brasileirao.api.service;

import brasileirao.api.dto.CardDto;
import brasileirao.api.dto.SubstitutionDto;
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
   * @param matchId Identificador da partida.
   * @return Instância de {@link MatchDto}.
   * @throws ServiceException Caso a partida não seja encontrada.
   */
  MatchDto findById(Long matchId) throws ServiceException;


  /**
   * Insere um gol em uma partida.
   *
   * @param matchId Id da partida onde o gol será inserido.
   * @param goalInputDto Dto com as informações do gol.
   * @return Dto com os dados do gol inserido.
   * @throws ServiceException Exceção que pode ser lançada ao não encontrar uma entidade.
   */
  GoalDto insertGoalInMatch(Long matchId, GoalInputDto goalInputDto) throws ServiceException;

  /**
   * Insere um cartão em uma partida.
   *
   * @param matchId Id da partida onde o cartão será inserido.
   * @param cardInputDto Dto com as informações do cartão a ser inserido.
   * @return Instância de {@link CardDto} com os dados do cartão inserido.
   * @throws ServiceException
   */
  CardDto insertCardInMatch(Long matchId, CardInputDto cardInputDto) throws ServiceException;

  /**
   * Insere uma substituição em uma partida.
   *
   * @param matchId Id da partida onde a substituição será inserida.
   * @param substitutionInputDto Dto com as informações da substituição a ser inserida.
   * @return Instância de {@link SubstitutionDto} com os dados da substituição inserida.
   * @throws ServiceException
   */
  SubstitutionDto insertSubstitutionInMatch(Long matchId, SubstitutionInputDto substitutionInputDto)
      throws ServiceException;

  /**
   * @param matchInputDto Dto com as informações da partida a ser inserida.
   * @throws ServiceException Exceção caso não sejam encontrados os times.
   * @throws ParseException Excecão que pode ser lançada ao converter a data.
   */
  void insertMatch(MatchInputDto matchInputDto) throws ServiceException, ParseException;

}
