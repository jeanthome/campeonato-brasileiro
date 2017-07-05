package brasileirao.api.dto;

import org.springframework.hateoas.ResourceSupport;

public class MatchDto extends ResourceSupport {

   /**
    * Id da partida.
    */
   private Long id;

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

   public void setId(Long id) {
      this.id = id;
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
}
