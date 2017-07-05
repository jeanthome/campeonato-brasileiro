package brasileirao.api.persistence;

import brasileirao.api.enums.StadiumEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

//TODO Adicionar campeonado.

/**
 * Representa uma partida de um campeonato.
 */
@Entity
@Table(name = "SOCCER_MATCH")
public class Match {

   /**
    * Id da partida.
    */
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   /**
    * A rodada onde a partida foi realizada.
    */
   @NotNull
   @Column(name = "ROUND_NUMBER")
   private Long roundNumber;

   /**
    * O time mandante.
    */
   @NotNull
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "HOME_CLUB_ID")
   private Club homeClub;

   /**
    * O time visitante.
    */
   @NotNull
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "VISITOR_CLUB_ID")
   private Club visitorClub;

   /**
    * Lista de gols do time mandante.
    */
   @OneToMany
   @JoinTable(name = "MATCH_GOAL", joinColumns = {@JoinColumn(name = "MATCH_ID")},
           inverseJoinColumns = {@JoinColumn(name = "GOAL_ID")})
   private List<Goal> homeGoals;

   /**
    * Lista de gols do time visitante.
    */
   @OneToMany
   @JoinTable(name = "MATCH_GOAL", joinColumns = {@JoinColumn(name = "MATCH_ID")},
           inverseJoinColumns = {@JoinColumn(name = "GOAL_ID")})
   private List<Goal> visitorGoals;

   /**
    * Lista de jogadores titulares do clube mandante.
    */
   @OneToMany
   @JoinTable(name = "MATCH_HOME_STARTING_PLAYER", joinColumns = {@JoinColumn(name = "MATCH_ID")},
           inverseJoinColumns = {@JoinColumn(name = "PLAYER_ID")})
   private List<Player> homeClubStartingPlayers;

   /**
    * Lista de jogadores titulares do clube visitante.
    */
   @OneToMany
   @JoinTable(name = "MATCH_VISITOR_STARTING_PLAYER",
           joinColumns = {@JoinColumn(name = "MATCH_ID")},
           inverseJoinColumns = {@JoinColumn(name = "PLAYER_ID")})
   private List<Player> visitorClubStartingPlayers;

   /**
    * Lista de jogadores reservas do clube mandante.
    */
   @OneToMany
   @JoinTable(name = "MATCH_HOME_SUBSTITUTE_PLAYER", joinColumns = {@JoinColumn(name = "MATCH_ID")},
           inverseJoinColumns = {@JoinColumn(name = "PLAYER_ID")})
   private List<Player> homeClubSubstitutePlayers;

   /**
    * Lista de jogadores reservas do clube visitante.
    */
   @OneToMany
   @JoinTable(name = "MATCH_VISITOR_SUBSTITUTE_PLAYER",
           joinColumns = {@JoinColumn(name = "MATCH_ID")},
           inverseJoinColumns = {@JoinColumn(name = "PLAYER_ID")})
   private List<Player> visitorClubSubstitutePlayers;

   /**
    * Lista de cartões da equipe mandante.
    */
   @OneToMany
   @JoinTable(name = "MATCH_HOME_CARD", joinColumns = {@JoinColumn(name = "MATCH_ID")},
           inverseJoinColumns = {@JoinColumn(name = "CARD_ID")})
   private List<Card> homeClubCardList;

   /**
    * Lista de cartões da equipe visitante.
    */
   @JoinTable(name = "MATCH_VISITOR_CARD", joinColumns = {@JoinColumn(name = "MATCH_ID")},
           inverseJoinColumns = {@JoinColumn(name = "CARD_ID")})
   @OneToMany
   private List<Card> visitorClubCardList;

   /**
    * Lista de substituicoes do clube mandante.
    */
   @OneToMany
   @JoinTable(name = "MATCH_HOME_SUBSTITUTION", joinColumns = {@JoinColumn(name = "MATCH_ID")},
           inverseJoinColumns = {@JoinColumn(name = "SUBSTITUTION_ID")})
   private List<Substitution> homeClubSubstitutionList;

   /**
    * Lista de substituicoes do clube visitante.
    */
   @OneToMany
   @JoinTable(name = "MATCH_VISITOR_SUBSTITUTION", joinColumns = {@JoinColumn(name = "MATCH_ID")},
           inverseJoinColumns = {@JoinColumn(name = "SUBSTITUTION_ID")})
   private List<Substitution> visitorClubSubstitutionList;

   /**
    * Estádio onde a partida foi realizada.
    */
   @NotNull
   @Column(name = "STADIUM")
   private StadiumEnum stadiumEnum;

   /**
    * Data da partida
    */
   @NotNull
   @Column(name = "KICK_OFF")
   private Date kickOff;

   public Long getId() {
      return id;
   }

   public Long getRoundNumber() {
      return roundNumber;
   }

   public void setRoundNumber(Long roundNumber) {
      this.roundNumber = roundNumber;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public StadiumEnum getStadiumEnum() {
      return stadiumEnum;
   }

   public void setStadiumEnum(StadiumEnum stadiumEnum) {
      this.stadiumEnum = stadiumEnum;
   }

   public Club getHomeClub() {
      return homeClub;
   }

   public void setHomeClub(Club homeClub) {
      this.homeClub = homeClub;
   }

   public Club getVisitorClub() {
      return visitorClub;
   }

   public void setVisitorClub(Club visitorClub) {
      this.visitorClub = visitorClub;
   }

   public List<Goal> getHomeGoals() {
      return homeGoals;
   }

   public void setHomeGoals(List<Goal> homeGoals) {
      this.homeGoals = homeGoals;
   }

   public List<Goal> getVisitorGoals() {
      return visitorGoals;
   }

   public void setVisitorGoals(List<Goal> visitorGoals) {
      this.visitorGoals = visitorGoals;
   }

   public List<Player> getHomeClubStartingPlayers() {
      return homeClubStartingPlayers;
   }

   public void setHomeClubStartingPlayers(List<Player> homeClubStartingPlayers) {
      this.homeClubStartingPlayers = homeClubStartingPlayers;
   }

   public List<Player> getVisitorClubStartingPlayers() {
      return visitorClubStartingPlayers;
   }

   public void setVisitorClubStartingPlayers(List<Player> visitorClubStartingPlayers) {
      this.visitorClubStartingPlayers = visitorClubStartingPlayers;
   }

   public List<Player> getHomeClubSubstitutePlayers() {
      return homeClubSubstitutePlayers;
   }

   public void setHomeClubSubstitutePlayers(List<Player> homeClubSubstitutePlayers) {
      this.homeClubSubstitutePlayers = homeClubSubstitutePlayers;
   }

   public List<Player> getVisitorClubSubstitutePlayers() {
      return visitorClubSubstitutePlayers;
   }

   public void setVisitorClubSubstitutePlayers(List<Player> visitorClubSubstitutePlayers) {
      this.visitorClubSubstitutePlayers = visitorClubSubstitutePlayers;
   }

   public List<Card> getHomeClubCardList() {
      return homeClubCardList;
   }

   public void setHomeClubCardList(List<Card> homeClubCardList) {
      this.homeClubCardList = homeClubCardList;
   }

   public List<Card> getVisitorClubCardList() {
      return visitorClubCardList;
   }

   public void setVisitorClubCardList(List<Card> visitorClubCardList) {
      this.visitorClubCardList = visitorClubCardList;
   }

   public List<Substitution> getHomeClubSubstitutionList() {
      return homeClubSubstitutionList;
   }

   public void setHomeClubSubstitutionList(List<Substitution> homeClubSubstitutionList) {
      this.homeClubSubstitutionList = homeClubSubstitutionList;
   }

   public List<Substitution> getVisitorClubSubstitutionList() {
      return visitorClubSubstitutionList;
   }

   public void setVisitorClubSubstitutionList(List<Substitution> visitorClubSubstitutionList) {
      this.visitorClubSubstitutionList = visitorClubSubstitutionList;
   }

   public Date getKickOff() {
      return kickOff;
   }

   public void setKickOff(Date kickOff) {
      this.kickOff = kickOff;
   }

}
