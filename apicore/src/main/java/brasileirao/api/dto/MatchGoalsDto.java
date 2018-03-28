package brasileirao.api.dto;

import java.util.List;

/**
 * DTO usado apra representar os gols de uma partida.
 */
public class MatchGoalsDto {

  /**
   * Lista de gols do clube mandate.
   */
  private List<GoalDto> homeClubGoals;

  /**
   * Lista de gols do clube visitante.
   */
  private List<GoalDto> visitorClubGoals;

  public List<GoalDto> getHomeClubGoals() {
    return homeClubGoals;
  }

  public void setHomeCLubeGoals(List<GoalDto> homeClubGoals) {
    this.homeClubGoals = homeClubGoals;
  }

  public List<GoalDto> getVisitorClubGoals() {
    return visitorClubGoals;
  }

  public void setVisitorClubGoals(List<GoalDto> visitorClubGoals) {
    this.visitorClubGoals = visitorClubGoals;
  }
}
