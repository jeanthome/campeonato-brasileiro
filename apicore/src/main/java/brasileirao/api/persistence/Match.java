package brasileirao.api.persistence;

import brasileirao.api.enums.StadiumEnum;

import javax.persistence.CascadeType;
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
   @JoinTable(name = "MATCH_HOME_GOAL", joinColumns = {@JoinColumn(name = "MATCH_ID")},
           inverseJoinColumns = {@JoinColumn(name = "GOAL_ID")})
   private List<Goal> homeClubGoals;

   /**
    * Lista de gols do time visitante.
    */
   @OneToMany
   @JoinTable(name = "MATCH_VISITOR_GOAL", joinColumns = {@JoinColumn(name = "MATCH_ID")},
           inverseJoinColumns = {@JoinColumn(name = "GOAL_ID")})
   private List<Goal> visitorClubGoals;

   /**
    * Lista de jogadores titulares do clube mandante.
    */
   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinTable(name = "MATCH_HOME_STARTING_PLAYER", joinColumns = {@JoinColumn(name = "MATCH_ID")},
           inverseJoinColumns = {@JoinColumn(name = "PLAYER_IN_MATCH_ID")})
   private List<PlayerInMatch> homeClubStartingPlayers;

   /**
    * Lista de jogadores titulares do clube visitante.
    */
   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinTable(name = "MATCH_VISITOR_STARTING_PLAYER",
           joinColumns = {@JoinColumn(name = "MATCH_ID")},
           inverseJoinColumns = {@JoinColumn(name = "PLAYER_IN_MATCH_ID")})
   private List<PlayerInMatch> visitorClubStartingPlayers;

   /**
    * Lista de jogadores reservas do clube mandante.
    */
   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinTable(name = "MATCH_HOME_SUBSTITUTE_PLAYER", joinColumns = {@JoinColumn(name = "MATCH_ID")},
           inverseJoinColumns = {@JoinColumn(name = "PLAYER_IN_MATCH_ID")})
   private List<PlayerInMatch> homeClubSubstitutePlayers;

   /**
    * Lista de jogadores reservas do clube visitante.
    */
   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinTable(name = "MATCH_VISITOR_SUBSTITUTE_PLAYER",
           joinColumns = {@JoinColumn(name = "MATCH_ID")},
           inverseJoinColumns = {@JoinColumn(name = "PLAYER_IN_MATCH_ID")})
   private List<PlayerInMatch> visitorClubSubstitutePlayers;

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

   /**
    * Indica se a partida já foi finalizada.
    */
   @Column(name = "FINISHED")
   private Boolean finished;

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

   public List<Goal> getHomeClubGoals() {
      return homeClubGoals;
   }

   public void setHomeClubGoals(List<Goal> homeClubGoals) {
      this.homeClubGoals = homeClubGoals;
   }

   public List<Goal> getVisitorClubGoals() {
      return visitorClubGoals;
   }

   public void setVisitorClubGoals(List<Goal> visitorClubGoals) {
      this.visitorClubGoals = visitorClubGoals;
   }

   public List<PlayerInMatch> getHomeClubStartingPlayers() {
      return homeClubStartingPlayers;
   }


   public void setHomeClubStartingPlayers(List<PlayerInMatch> homeClubStartingPlayers) {
      this.homeClubStartingPlayers.clear();
      if (homeClubStartingPlayers != null) {
         this.homeClubStartingPlayers.addAll(homeClubStartingPlayers);
      }
   }

   public List<PlayerInMatch> getVisitorClubStartingPlayers() {
      return visitorClubStartingPlayers;
   }

   public void setVisitorClubStartingPlayers(List<PlayerInMatch> visitorClubStartingPlayers) {
      this.visitorClubStartingPlayers.clear();
      if (visitorClubStartingPlayers != null) {
         this.visitorClubStartingPlayers.addAll(visitorClubStartingPlayers);
      }
   }

   public List<PlayerInMatch> getHomeClubSubstitutePlayers() {
      return homeClubSubstitutePlayers;
   }

   public void setHomeClubSubstitutePlayers(List<PlayerInMatch> homeClubSubstitutePlayers) {
      this.homeClubSubstitutePlayers.clear();
      if (homeClubSubstitutePlayers != null) {
         this.homeClubSubstitutePlayers.addAll(homeClubSubstitutePlayers);
      }
   }

   public List<PlayerInMatch> getVisitorClubSubstitutePlayers() {
      return visitorClubSubstitutePlayers;
   }

   public void setVisitorClubSubstitutePlayers(List<PlayerInMatch> visitorClubSubstitutePlayers) {
      this.visitorClubSubstitutePlayers.clear();
      if (visitorClubSubstitutePlayers != null) {
         this.visitorClubSubstitutePlayers.addAll(visitorClubSubstitutePlayers);
      }
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

   public Boolean getFinished() {
      return finished;
   }

   public void setFinished(Boolean finished) {
      this.finished = finished;
   }


}
