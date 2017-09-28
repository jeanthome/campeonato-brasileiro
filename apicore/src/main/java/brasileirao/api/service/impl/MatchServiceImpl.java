package brasileirao.api.service.impl;

import brasileirao.api.dao.ClubDao;
import brasileirao.api.dao.MatchDao;
import brasileirao.api.dto.GoalInputDto;
import brasileirao.api.dto.MatchDto;
import brasileirao.api.dto.MatchInputDto;
import brasileirao.api.dto.MatchMinDto;
import brasileirao.api.enums.ClubTypeEnum;
import brasileirao.api.enums.ServiceExceptionMessageEnum;
import brasileirao.api.exception.ServiceException;
import brasileirao.api.helper.DateHelper;
import brasileirao.api.persistence.Club;
import brasileirao.api.persistence.Goal;
import brasileirao.api.persistence.Match;
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

      matchDto.setHomeClubStartingPlayers(this.playerInMatchService
              .convertPlayerInMatchListToPlayerMinDtoList(match.getHomeClubStartingPlayers()));

      matchDto.setHomeClubSubstitutePlayers(this.playerInMatchService
              .convertPlayerInMatchListToPlayerMinDtoList(match.getHomeClubSubstitutePlayers()));

      matchDto.setVisitorClubStartingPlayers(this.playerInMatchService
              .convertPlayerInMatchListToPlayerMinDtoList(match.getVisitorClubStartingPlayers()));

      matchDto.setVisitorClubSubstitutePlayers(this.playerInMatchService
              .convertPlayerInMatchListToPlayerMinDtoList(match.getVisitorClubSubstitutePlayers()));


      matchDto.setHomeClubGoals(this.goalService.convertGoalListToGoalDtoList(match
              .getHomeGoals()));

      matchDto.setVisitorClubGoals(this.goalService.convertGoalListToGoalDtoList(match
              .getVisitorGoals()));

      return matchDto;
   }


   /**
    * Converte uma entidade {@link Match} em seu D.T.O. {@link MatchMinDto}.
    *
    * @param match Entidade a ser convertida.
    * @return o DTO convertido.
    */
   public MatchMinDto convertMatchToMatchMinDto(Match match) {
      final ModelMapper modelMapper = new ModelMapper();
      final MatchMinDto matchMinDto = modelMapper.map(match, MatchMinDto.class);
      matchMinDto.setIdentifier(match.getId());
      matchMinDto.setHomeClub(this.clubService.convertClubToClubDto(match.getHomeClub()));
      matchMinDto.setVisitorClub(this.clubService.convertClubToClubDto(match.getVisitorClub()));
      matchMinDto.setStadiumName(match.getStadiumEnum().getName());
      matchMinDto.setKickOff(DateHelper.getFormattedDate(match.getKickOff()));
      matchMinDto.setHour(DateHelper.getFormattedHour(match.getKickOff()));
      return matchMinDto;
   }

   /**
    * Insere um gol em uma partida.
    *
    * @param goalInputDto Dto de entrada com os dados do gol.
    * @throws ServiceException Exceção das classes de serviço.
    */
   public void insertGoalInMatch(GoalInputDto goalInputDto) throws ServiceException {

      final Goal goal = this.goalService.convertGoalInputDtoToGoal(goalInputDto);
      final Match match = this.matchDao.findById(goalInputDto.getMatch());

      if (match == null) {
         throw new ServiceException(ServiceExceptionMessageEnum.MATCH_NOT_FOUND.getMessage());
      }

      if (goalInputDto.getClubType().equals(ClubTypeEnum.HOME_CLUB.getClubType())) {

         if (goalInputDto.getOwnGoal()) {
            match.getVisitorGoals().add(goal);
         } else {
            match.getHomeGoals().add(goal);
         }

      } else {

         if (goalInputDto.getOwnGoal()) {
            match.getHomeGoals().add(goal);
         } else {
            match.getVisitorGoals().add(goal);
         }
      }
      matchDao.save(match);
   }
}
