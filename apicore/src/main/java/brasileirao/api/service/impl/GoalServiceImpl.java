package brasileirao.api.service.impl;

import brasileirao.api.dao.GoalDao;
import brasileirao.api.dao.PlayerInMatchDao;
import brasileirao.api.dto.GoalDto;
import brasileirao.api.dto.input.GoalInputDto;
import brasileirao.api.enums.ServiceExceptionMessageEnum;
import brasileirao.api.exception.ServiceException;
import brasileirao.api.persistence.Goal;
import brasileirao.api.persistence.PlayerInMatch;
import brasileirao.api.service.GoalService;
import brasileirao.api.service.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementação da classe de serviços da entidade Goal.
 */
@Service
public class GoalServiceImpl implements GoalService {

   /**
    * Classe de acesso de dados da entidade Goal.
    */
   @Autowired
   private GoalDao goalDao;

   /**
    * Classe de serviços da entidade Player.
    */
   @Autowired
   private PlayerService playerService;

   /**
    * Classe de acesso de dados da entidade {@link brasileirao.api.persistence.PlayerInMatch}
    */
   @Autowired
   private PlayerInMatchDao playerInMatchDao;

   @Override
   public Goal save(Goal goal) {
      return this.goalDao.save(goal);
   }

   @Override
   public Goal convertGoalInputDtoToGoal(GoalInputDto goalInputDto) throws ServiceException {
      final ModelMapper modelMapper = new ModelMapper();
      final Goal goal = modelMapper.map(goalInputDto, Goal.class);
      goal.setId(null);

      final PlayerInMatch goalOwnerPlayer =
              this.playerInMatchDao.findOne(goalInputDto.getGoalOwner());

      if (goalOwnerPlayer == null) {
         throw new ServiceException(ServiceExceptionMessageEnum.PLAYER_NOT_FOUND.getMessage());
      }

      goal.setOwner(goalOwnerPlayer);
      return goal;
   }

   @Override
   public GoalDto convertGoalToGoalDto(Goal goal) {
      final ModelMapper modelMapper = new ModelMapper();
      final GoalDto goalDto = modelMapper.map(goal, GoalDto.class);
      return goalDto;
   }

   @Override
   public List<GoalDto> convertGoalListToGoalDtoList(List<Goal> goalList) {

      final List<GoalDto> goalDtoList = new ArrayList<>();
      for (Goal goal : goalList) {
         final GoalDto goalDto = this.convertGoalToGoalDto(goal);
         goalDtoList.add(goalDto);
      }

      return goalDtoList;
   }
}
