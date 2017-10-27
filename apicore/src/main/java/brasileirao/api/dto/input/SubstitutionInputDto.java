package brasileirao.api.dto.input;

import brasileirao.api.enums.ClubTypeEnum;
import brasileirao.api.enums.HalfEnum;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * DTO com as informações de inserção de uma nova substituição.
 */
public class SubstitutionInputDto {
   /**
    * Id da partida onde o gol foi marcado.
    */
   @NotNull
   private Long matchId;

   /**
    * Clube que fez a substituição. (HOMECLUB || VISITORCLUB)
    *
    * @see brasileirao.api.enums.ClubTypeEnum
    */
   @NotNull
   private ClubTypeEnum clubType;

   /**
    * Jogador que saiu da partida.
    */
   @NotNull
   private Long playerWhoLeaves;


   /**
    * Jogador que entrou na partida.
    */
   @NotNull
   private Long playerWhoEnters;
   /**
    * Tempo em que o gol foi marcado.
    */
   @NotNull
   private HalfEnum half;

   /**
    * Minuto em que a substituição foi realizada.
    */
   @Min(0)
   @NotNull
   private Long minute;

   public Long getMatchId() {
      return matchId;
   }

   public void setMatchId(Long matchId) {
      this.matchId = matchId;
   }

   public ClubTypeEnum getClubType() {
      return clubType;
   }

   public void setClubType(ClubTypeEnum clubType) {
      this.clubType = clubType;
   }

   public Long getPlayerWhoLeaves() {
      return playerWhoLeaves;
   }

   public void setPlayerWhoLeaves(Long playerWhoLeaves) {
      this.playerWhoLeaves = playerWhoLeaves;
   }

   public Long getPlayerWhoEnters() {
      return playerWhoEnters;
   }

   public void setPlayerWhoEnters(Long playerWhoEnters) {
      this.playerWhoEnters = playerWhoEnters;
   }

   public HalfEnum getHalf() {
      return half;
   }

   public void setHalf(HalfEnum half) {
      this.half = half;
   }

   public Long getMinute() {
      return minute;
   }

   public void setMinute(Long minute) {
      this.minute = minute;
   }
}
