package brasileirao.api.service;

import brasileirao.api.dto.GoalDto;
import brasileirao.api.dto.GoalInputDto;
import brasileirao.api.exception.ServiceException;
import brasileirao.api.persistence.Goal;

import java.util.List;

/**
 * Interface da classe de serviços da entidade Goal;
 */
public interface GoalService {


   /**
    * Persiste no banco uma instância da entidade Goal.
    *
    * @param goal A entidade a ser persistida.
    * @return Instância persistida.
    * @see Goal
    */
   Goal save(Goal goal);

   /**
    * Converte uma instância de GoalInputDto em uma de Goal.
    *
    * @param goalInputDto Instância a ser convertida.
    * @return Instância da entidade Goal.
    * @throws ServiceException Exceção caso a instância do jogador que fez o gol não for encontrada.
    */
   Goal convertGoalInputDtoToGoal(GoalInputDto goalInputDto) throws ServiceException;

   /**
    * Converte entidade Goal em uma entidade GoalDto.
    *
    * @param goal Entidade Goal a ser convertida.
    * @return Entidade GoalDto convertida.
    */
   GoalDto convertGoalToGoalDto(Goal goal);

   /**
    * Converte uma lista de Goal em uma lista de GoalDto.
    *
    * @param goalList Lista de Goal a ser convertida.
    * @return Lista de GoalDto.
    */
   List<GoalDto> convertGoalListToGoalDtoList(List<Goal> goalList);


}
