package brasileirao.api.persistence;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Representaçao do desempenho de um clube em uma temporada do campeonato.
 */
@Entity(name = "PERFORMANCE")
public class Performance {

  /**
   * Identificador da entidade.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  /**
   * O clube cujo desempenho esta sendo mapeado.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CLUB_ID")
  private Club club;

  /**
   * Temporada em que o clube teve tal desempenho.
   */
  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "SEASON_ID")
  private Season season;

  /**
   * Numero de pontos na temporada.
   */
  @NotNull
  @Column(name = "POINTS")
  private Long Points;

  /**
   * Numero de partidadas jogadas.
   */
  @NotNull
  @Column(name = "MATCH_PLAYED")
  private Long matchesPlayed;

  /**
   * Numero de vitorias na temporada.
   */
  @NotNull
  @Column(name = "NUMBER_OF_VICTORIES")
  private Long numberOfVictories;

  /**
   * Numero de derrotas na temporada.
   */
  @NotNull
  @Column(name = "NUMBER_OF_DEFEATS")
  private Long numberOfDefeats;

  /**
   * Numero de empates na temporada.
   */
  @NotNull
  @Column(name = "NUMBER_OF_DRAWS")
  private Long numberOfDraws;

  /**
   * Numero de gols marcados.
   */
  @NotNull
  @Column(name = "GOALS_FOR")
  private Long goalsFor;

  /**
   * Numero de gols sofridos.
   */
  @NotNull
  @Column(name = "GOALS_AGAINST")
  private Long goalsAgainst;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Club getClub() {
    return club;
  }

  public void setClub(Club club) {
    this.club = club;
  }

  public Season getSeason() {
    return season;
  }

  public void setSeason(Season season) {
    this.season = season;
  }

  public Long getPoints() {
    return Points;
  }

  public void setPoints(Long points) {
    Points = points;
  }

  public Long getMatchesPlayed() {
    return matchesPlayed;
  }

  public void setMatchesPlayed(Long matchesPlayed) {
    this.matchesPlayed = matchesPlayed;
  }

  public Long getNumberOfVictories() {
    return numberOfVictories;
  }

  public void setNumberOfVictories(Long numberOfVictories) {
    this.numberOfVictories = numberOfVictories;
  }

  public Long getNumberOfDefeats() {
    return numberOfDefeats;
  }

  public void setNumberOfDefeats(Long numberOfDefeats) {
    this.numberOfDefeats = numberOfDefeats;
  }

  public Long getNumberOfDraws() {
    return numberOfDraws;
  }

  public void setNumberOfDraws(Long numberOfDraws) {
    this.numberOfDraws = numberOfDraws;
  }

  public Long getGoalsFor() {
    return goalsFor;
  }

  public void setGoalsFor(Long goalsFor) {
    this.goalsFor = goalsFor;
  }

  public Long getGoalsAgainst() {
    return goalsAgainst;
  }

  public void setGoalsAgainst(Long goalsAgainst) {
    this.goalsAgainst = goalsAgainst;
  }
}
