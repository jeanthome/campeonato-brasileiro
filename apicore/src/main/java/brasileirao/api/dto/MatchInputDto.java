package brasileirao.api.dto;

import brasileirao.api.enums.StadiumEnum;

import javax.validation.constraints.NotNull;

/**
 * DTO usado para cadastro de novas partidas.
 */
public class MatchInputDto {

   /**
    * Número da rodada em que a partida foi realizada.
    */
   @NotNull
   private Long roundNumber;

   /**
    * Data e hora da realização da partida.
    */
   @NotNull
   private String kickOff;

   /**
    * Estádio onde a partida foi realizada
    */
   @NotNull
   private StadiumEnum stadiumEnum;

   /**
    * Id do clube mandante.
    */
   @NotNull
   private Long homeClubId;

   /**
    * Id do clube visitante
    */
   @NotNull
   private Long visitorClubId;

   public Long getRoundNumber() {
      return roundNumber;
   }

   public void setRoundNumber(Long roundNumber) {
      this.roundNumber = roundNumber;
   }

   public String getKickOff() {
      return kickOff;
   }

   public void setKickOff(String kickOff) {
      this.kickOff = kickOff;
   }

   public StadiumEnum getStadiumEnum() {
      return stadiumEnum;
   }

   public void setStadiumEnum(StadiumEnum stadiumEnum) {
      this.stadiumEnum = stadiumEnum;
   }

   public Long getHomeClubId() {
      return homeClubId;
   }

   public void setHomeClubId(Long homeClubId) {
      this.homeClubId = homeClubId;
   }

   public Long getVisitorClubId() {
      return visitorClubId;
   }

   public void setVisitorClubId(Long visitorClubId) {
      this.visitorClubId = visitorClubId;
   }
}
