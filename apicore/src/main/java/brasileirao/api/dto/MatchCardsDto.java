package brasileirao.api.dto;

import java.util.List;

/**
 * DTO usado para representar os cartões de uma partida.
 */
public class MatchCardsDto {


  /**
   * Lista com os cartões aplicados aos jogadores do time mandante.
   */
  private List<CardDto> homeClubCards;

  /**
   * Lista com os cartões aplicados aos jogadores do time visitante.
   */
  private List<CardDto> visitorClubCards;

  public List<CardDto> getHomeClubCards() {
    return homeClubCards;
  }

  public void setHomeClubCards(List<CardDto> homeClubCards) {
    this.homeClubCards = homeClubCards;
  }

  public List<CardDto> getVisitorClubCards() {
    return visitorClubCards;
  }

  public void setVisitorClubCards(List<CardDto> visitorClubCards) {
    this.visitorClubCards = visitorClubCards;
  }
}
