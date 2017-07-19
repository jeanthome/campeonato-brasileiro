package brasileirao.api.dto;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class MatchDto extends ResourceSupport {

   /**
    * Idenficador da partida.
    */
   private Long identificator;

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

   public Long getIdentificator() {
      return identificator;
   }

   public void setIdentificator(Long identificator) {
      this.identificator = identificator;
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
}
