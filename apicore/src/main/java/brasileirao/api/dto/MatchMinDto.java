package brasileirao.api.dto;

import org.springframework.hateoas.ResourceSupport;

/**
 * DTO com a representação mínima de uma partida.
 */
public class MatchMinDto extends ResourceSupport {
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
    * O número de gol do time mandante.
    */
   private Long homeClubGoals;

   /**
    * O número de gol do time visitante;
    */
   private Long visitorClubGoals;

   /**
    * O estádio onde a partida foi realizada.
    */
   private String stadiumName;

   /**
    * A data de realização da partida.
    */
   private String kickOff;

   /**
    * A hora de realização da partida.
    */
   private String hour;

   /**
    * Indica se a partida já foi finalizada.
    */
   private Boolean finished;

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

   public String getStadiumName() {
      return stadiumName;
   }

   public void setStadiumName(String stadiumName) {
      this.stadiumName = stadiumName;
   }

   public String getKickOff() {
      return kickOff;
   }

   public void setKickOff(String kickOff) {
      this.kickOff = kickOff;
   }

   public String getHour() {
      return hour;
   }

   public void setHour(String hour) {
      this.hour = hour;
   }

   public Boolean getFinished() {
      return finished;
   }

   public void setFinished(Boolean finished) {
      this.finished = finished;
   }

   public Long getHomeClubGoals() {
      return homeClubGoals;
   }

   public void setHomeClubGoals(Long homeClubGoals) {
      this.homeClubGoals = homeClubGoals;
   }

   public Long getVisitorClubGoals() {
      return visitorClubGoals;
   }

   public void setVisitorClubGoals(Long visitorClubGoals) {
      this.visitorClubGoals = visitorClubGoals;
   }
}
