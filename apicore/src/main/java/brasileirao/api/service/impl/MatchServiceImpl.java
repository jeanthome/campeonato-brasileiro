package brasileirao.api.service.impl;

import brasileirao.api.dao.ClubDao;
import brasileirao.api.dao.GoalDao;
import brasileirao.api.dao.MatchDao;
import brasileirao.api.dto.CardInputDto;
import brasileirao.api.dto.GoalDto;
import brasileirao.api.dto.GoalInputDto;
import brasileirao.api.dto.MatchDto;
import brasileirao.api.dto.MatchInputDto;
import brasileirao.api.dto.MatchMinDto;
import brasileirao.api.enums.ClubTypeEnum;
import brasileirao.api.enums.GoalTypeEnum;
import brasileirao.api.enums.ServiceExceptionMessageEnum;
import brasileirao.api.exception.ServiceException;
import brasileirao.api.helper.DateHelper;
import brasileirao.api.persistence.Card;
import brasileirao.api.persistence.Club;
import brasileirao.api.persistence.Goal;
import brasileirao.api.persistence.Match;
import brasileirao.api.service.CardService;
import brasileirao.api.service.ClubService;
import brasileirao.api.service.GoalService;
import brasileirao.api.service.MatchService;
import brasileirao.api.service.PlayerInMatchService;
import brasileirao.api.service.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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

   /**
    * Objeto de acesso de dados da classe {@link brasileirao.api.persistence.Club}.
    */
   @Autowired
   private ClubDao clubDao;

   /**
    * Classe de serviços da entidade Player.
    */
   @Autowired
   private PlayerService playerService;

   /**
    * Classe de serviços da entidade PlayerInMatch.
    */
   @Autowired
   private PlayerInMatchService playerInMatchService;

   /**
    * Classe de serviços da entidae Goal.
    */
   @Autowired
   private GoalService goalService;

   /**
    * Classe de serviços da entidade {@link Club}
    */
   @Autowired
   private ClubService clubService;

   /**
    * Classe de acesso de dados da entidade Goal.
    */
   @Autowired
   private GoalDao goalDao;

   /**
    * Classe de serviços da entidade {@link Card}
    */
   @Autowired
   private CardService cardService;


   @Override
   public Match save(Match match) {
      return this.matchDao.save(match);
   }

   /**
    * Retorna as partidas de uma dada rodada.
    *
    * @param roundNumber O número da rodada.
    * @return List com as partidas encontradas.
    */
   @Override
   public List<MatchMinDto> getMatchesInRound(Long roundNumber) {

      final List<Match> matchList = this.matchDao.findByRoundNumberOrderByKickOffAsc(roundNumber);
      final List<MatchMinDto> matchMinDtos = new ArrayList<>();
      for (Match match : matchList) {
         matchMinDtos.add(this.convertMatchToMatchMinDto(match));
      }

      return matchMinDtos;
   }

   @Override
   public void insertMatch(MatchInputDto matchInputDto) throws ServiceException, ParseException {

      final Club homeClub = this.clubDao.findById(matchInputDto.getHomeClubId());
      final Club visitorClub = this.clubDao.findById(matchInputDto.getVisitorClubId());

      /* Verifica se os clubes existem */
      if (homeClub == null || visitorClub == null) {
         throw new ServiceException(ServiceExceptionMessageEnum.CLUB_NOT_FOUND.getMessage());
      }

      /* Cria instância da entidade Match */
      final Match match = new Match();
      match.setStadiumEnum(matchInputDto.getStadiumEnum());
      match.setRoundNumber(matchInputDto.getRoundNumber());
      match.setHomeClub(homeClub);
      match.setVisitorClub(visitorClub);
      match.setKickOff(DateHelper.convertStringToDate(matchInputDto.getKickOff()));
      match.setFinished(false);

      /* Persiste a nova instância */
      this.matchDao.save(match);
   }


   /**
    * Busca uma partida usando como critério o seu identifador.
    *
    * @param matchId Identificador da partida
    * @return Instância de {@link Match} se existe. null, caso contrário.
    */
   @Override
   public Match findById(Long matchId) {
      return this.matchDao.findById(matchId);
   }

   /**
    * Converte uma instância de <i>Match</i> ao seu respectivo DTO.
    *
    * @param match Instância da classe <i>Club</i>, que será convertida em DTO.
    * @return Instância de <i>ClubDto</i>
    */
   public MatchDto convertMatchToDto(Match match) {
      final ModelMapper modelMapper = new ModelMapper();
      final MatchDto matchDto = modelMapper.map(match, MatchDto.class);
      matchDto.setIdentificator(match.getId());
      matchDto.getHomeClub().setIdentificator(match.getHomeClub().getId());
      matchDto.getVisitorClub().setIdentificator(match.getVisitorClub().getId());

      /**
       * Converte os jogadores em seus respectivos DTOs.
       */
      matchDto.setHomeClubStartingPlayers(this.playerInMatchService
              .convertPlayerInMatchListToPlayerMinDtoList(match.getHomeClubStartingPlayers()));

      matchDto.setHomeClubSubstitutePlayers(this.playerInMatchService
              .convertPlayerInMatchListToPlayerMinDtoList(match.getHomeClubSubstitutePlayers()));

      matchDto.setVisitorClubStartingPlayers(this.playerInMatchService
              .convertPlayerInMatchListToPlayerMinDtoList(match.getVisitorClubStartingPlayers()));

      matchDto.setVisitorClubSubstitutePlayers(this.playerInMatchService
              .convertPlayerInMatchListToPlayerMinDtoList(match.getVisitorClubSubstitutePlayers()));

      /**
       * Converte os gols em seus respectivos DTOs.
       */
      matchDto.setHomeClubGoals(this.goalService.convertGoalListToGoalDtoList(match
              .getHomeClubGoals()));

      matchDto.setVisitorClubGoals(this.goalService.convertGoalListToGoalDtoList(match
              .getVisitorClubGoals()));

      /**
       * Converte os cartões em seus respectivos DTOs.
       */

      matchDto.setHomeClubCards(this.cardService.convertCardListToCardDtoList(
              match.getHomeClubCardList()));
      matchDto.setVisitorClubCards(this.cardService.convertCardListToCardDtoList(
              match.getVisitorClubCardList()));

      return matchDto;
   }


   /**
    * Converte uma entidade {@link Match} em seu D.T.O. {@link MatchMinDto}.
    *
    * @param match Entidade a ser convertida.
    * @return o DTO convertido.
    */
   public MatchMinDto convertMatchToMatchMinDto(Match match) {

      final MatchMinDto matchMinDto = new MatchMinDto();

      matchMinDto.setIdentifier(match.getId());
      matchMinDto.setRoundNumber(match.getRoundNumber());
      matchMinDto.setFinished(match.getFinished());
      matchMinDto.setHomeClub(this.clubService.convertClubToClubDto(match.getHomeClub()));
      matchMinDto.setVisitorClub(this.clubService.convertClubToClubDto(match.getVisitorClub()));
      matchMinDto.setStadiumName(match.getStadiumEnum().getName());
      matchMinDto.setKickOff(DateHelper.getFormattedDate(match.getKickOff()));
      matchMinDto.setHour(DateHelper.getFormattedHour(match.getKickOff()));
      matchMinDto.setFinished(match.getFinished());

      /**
       * Se a partida já começou/terminou, adiciona o número de gols.
       */
      if (match.getKickOff().before(DateHelper.now())) {
         matchMinDto.setHomeClubGoals(Long.valueOf(match.getHomeClubGoals().size()));
         matchMinDto.setVisitorClubGoals(Long.valueOf(match.getVisitorClubGoals().size()));
      }

      return matchMinDto;
   }

   /**
    * Insere um gol em uma partida.
    *
    * @param goalInputDto Dto de entrada com os dados do gol.
    * @return Dto com as informações do gol inserido.
    * @throws ServiceException Exceção das classes de serviço.
    */
   public GoalDto insertGoalInMatch(GoalInputDto goalInputDto) throws ServiceException {

      final Goal goal = this.goalService.convertGoalInputDtoToGoal(goalInputDto);
      this.goalService.save(goal);
      final Match match = this.matchDao.findById(goalInputDto.getMatchId());

      if (match == null) {
         throw new ServiceException(ServiceExceptionMessageEnum.MATCH_NOT_FOUND.getMessage());
      }

      if (goalInputDto.getClubType().equals(ClubTypeEnum.HOME_CLUB)) {

         if (goalInputDto.getGoalType().equals(GoalTypeEnum.OWN_GOAL)) {
            match.getVisitorClubGoals().add(goal);
         } else {
            match.getHomeClubGoals().add(goal);
         }

      } else {
         if (goalInputDto.getGoalType().equals(GoalTypeEnum.OWN_GOAL)) {
            match.getHomeClubGoals().add(goal);
         } else {
            match.getVisitorClubGoals().add(goal);
         }
      }
      matchDao.save(match);

      return this.goalService.convertGoalToGoalDto(goal);
   }

   @Override
   public void insertCardInMatch(CardInputDto cardInputDto) throws ServiceException {

      final Card card = this.cardService.convertCardInputDtoToCard(cardInputDto);
      this.cardService.save(card);

      final Match match = this.matchDao.findById(cardInputDto.getMatchId());

      if (match == null) {
         throw new ServiceException(ServiceExceptionMessageEnum.MATCH_NOT_FOUND.getMessage());
      }

      if (cardInputDto.getClubType().equals(ClubTypeEnum.HOME_CLUB)) {
         match.getHomeClubCardList().add(card);
      } else if (cardInputDto.getClubType().equals(ClubTypeEnum.VISITOR_CLUB)) {
         match.getVisitorClubCardList().add(card);
      }

      matchDao.save(match);

   }
}
