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
   private Club homeClub;

   /**
    * O time visitante.
    */
   @NotNull
   @ManyToOne(fetch = FetchType.LAZY)
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
    * Estádio onde a partida foi realizada.
    */
   @NotNull
   @Column(name = "STADIUM")
   private StadiumEnum stadiumEnum;

   /**
    * Lista de jogadores titulares.
    */
   @OneToMany
   @JoinTable(name = "MATCH_STARTING_PLAYER", joinColumns = {@JoinColumn(name = "MATCH_ID")},
           inverseJoinColumns = {@JoinColumn(name = "PLAYER_ID")})
   private List<Player> startingPlayers;

   /**
    * Lista de jogadores reservas.
    */
   @OneToMany
   @JoinTable(name = "MATCH_SUBSTITUTE_PLAYER", joinColumns = {@JoinColumn(name = "MATCH_ID")},
           inverseJoinColumns = {@JoinColumn(name = "PLAYER_ID")})
   private List<Player> substitutePlayers;

   /**
    * Lista de cartões da partida.
    */
   @JoinTable(name = "MATCH_CARD", joinColumns = {@JoinColumn(name = "MATCH_ID")},
           inverseJoinColumns = {@JoinColumn(name = "CARD_ID")})
   @OneToMany
   private List<Card> cardList;

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

   public List<Player> getStartingPlayers() {
      return startingPlayers;
   }

   public void setStartingPlayers(List<Player> startingPlayers) {
      this.startingPlayers = startingPlayers;
   }

   public List<Player> getSubstitutePlayers() {
      return substitutePlayers;
   }

   public void setSubstitutePlayers(List<Player> substitutePlayers) {
      this.substitutePlayers = substitutePlayers;
   }

   public List<Card> getCardList() {
      return cardList;
   }

   public void setCardList(List<Card> cardList) {
      this.cardList = cardList;
   }

   public Date getKickOff() {
      return kickOff;
   }

   public void setKickOff(Date kickOff) {
      this.kickOff = kickOff;
   }

}
