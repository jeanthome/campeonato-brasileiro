package brasileirao.api.dto;

import brasileirao.api.persistence.Goal;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * DTO com as informações de uma partida.
 */
public class MatchDto extends ResourceSupport {

  /**
   * Idenficador da partida.
   */
  private Long identifier;

  /**
   * A rodada onde a partida foi realizada.
   */
  private Long roundNumber;

  /**
   * O time mandante.
   */
  private ClubDto homeClub;

  /**
   * O time visitante.
   */
  private ClubDto visitorClub;

  /**
   * Jogadores titulares da equipe mandante.
   */
  private List<PlayerMinDto> homeClubStartingPlayers;

  /**
   * Jogadores titulares da equipe mandante.
   */
  private List<PlayerMinDto> visitorClubStartingPlayers;

  /**
   * Jogadores reservas do time mandante.
   */
  private List<PlayerMinDto> homeClubSubstitutePlayers;

  /**
   * Jogadores reservas do time visitante.
   */
  private List<PlayerMinDto> visitorClubSubstitutePlayers;


  /**
   * Lista de gols do time mandante.
   */
  private List<GoalDto> homeClubGoals;

  /**
   * Lista de gols do time visitante.
   */
  private List<GoalDto> visitorClubGoals;

  /**
   * Lista com os cartões aplicados aos jogadores do time mandante.
   */
  private List<CardDto> homeClubCards;

  /**
   * Lista com os cartões aplicados aos jogadores do time visitante.
   */
  private List<CardDto> visitorClubCards;

  /**
   * Lista de substituições do time mandante.
   */
  private List<SubstitutionDto> homeClubSubstitutions;

  /**
   * Lista de substituições do time visitante.
   */
  private List<SubstitutionDto> visitorClubSubstitutions;

  public Long getIdentifier() {
    return identifier;
  }

  public void setIdentifier(Long identifier) {
    this.identifier = identifier;
  }

  public Long getRoundNumber() {
    return roundNumber;
  }

  public void setRoundNumber(Long roundNumber) {
    this.roundNumber = roundNumber;
  }

  public ClubDto getHomeClub() {
    return homeClub;
  }

  public void setHomeClub(ClubDto homeClub) {
    this.homeClub = homeClub;
  }

  public ClubDto getVisitorClub() {
    return visitorClub;
  }

  public void setVisitorClub(ClubDto visitorClub) {
    this.visitorClub = visitorClub;
  }

  public List<PlayerMinDto> getHomeClubStartingPlayers() {
    return homeClubStartingPlayers;
  }

  public void setHomeClubStartingPlayers(List<PlayerMinDto> homeClubStartingPlayers) {
    this.homeClubStartingPlayers = homeClubStartingPlayers;
  }

  public List<PlayerMinDto> getVisitorClubStartingPlayers() {
    return visitorClubStartingPlayers;
  }

  public void setVisitorClubStartingPlayers(List<PlayerMinDto> visitorClubStartingPlayers) {
    this.visitorClubStartingPlayers = visitorClubStartingPlayers;
  }

  public List<PlayerMinDto> getHomeClubSubstitutePlayers() {
    return homeClubSubstitutePlayers;
  }

  public void setHomeClubSubstitutePlayers(List<PlayerMinDto> homeClubSubstitutePlayers) {
    this.homeClubSubstitutePlayers = homeClubSubstitutePlayers;
  }

  public List<PlayerMinDto> getVisitorClubSubstitutePlayers() {
    return visitorClubSubstitutePlayers;
  }

  public void setVisitorClubSubstitutePlayers(List<PlayerMinDto> visitorClubSubstitutePlayers) {
    this.visitorClubSubstitutePlayers = visitorClubSubstitutePlayers;
  }

  public List<GoalDto> getHomeClubGoals() {
    return homeClubGoals;
  }

  public void setHomeClubGoals(List<GoalDto> homeClubGoals) {
    this.homeClubGoals = homeClubGoals;
  }

  public List<GoalDto> getVisitorClubGoals() {
    return visitorClubGoals;
  }

  public void setVisitorClubGoals(List<GoalDto> visitorClubGoals) {
    this.visitorClubGoals = visitorClubGoals;
  }

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

  public List<SubstitutionDto> getHomeClubSubstitutions() {
    return homeClubSubstitutions;
  }

  public void setHomeClubSubstitutions(List<SubstitutionDto> homeClubSubstitutions) {
    this.homeClubSubstitutions = homeClubSubstitutions;
  }

  public List<SubstitutionDto> getVisitorClubSubstitutions() {
    return visitorClubSubstitutions;
  }

  public void setVisitorClubSubstitutions(List<SubstitutionDto> visitorClubSubstitutions) {
    this.visitorClubSubstitutions = visitorClubSubstitutions;
  }
}
