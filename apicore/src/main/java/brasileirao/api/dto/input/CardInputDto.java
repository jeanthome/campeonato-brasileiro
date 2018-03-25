package brasileirao.api.dto.input;

import brasileirao.api.enums.CardEnum;
import brasileirao.api.enums.ClubTypeEnum;
import brasileirao.api.enums.HalfEnum;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * DTO que mapeia dados de entrada para inserção de cartão em partida.
 */
public class CardInputDto {

   /**
    * Clube dono do gol. (HOME_CLUB || VISITOR_CLUB)
    *
    * @see brasileirao.api.enums.ClubTypeEnum
    */
   @NotNull
   private ClubTypeEnum clubType;

   /**
    * O Id do jogador que recebeu o cartão.
    */
   @NotNull
   private Long cardOwner;

   /**
    * A cor do cartão.
    */
   @NotNull
   private CardEnum cardColor;

   /**
    * O tempo que o cartão foi aplicado.
    */
   @NotNull
   private HalfEnum half;

   /**
    * Minuto em que o cartão foi aplicado.
    */
   @Min(0)
   @NotNull
   private Long minute;

   /**
    * O motivo da aplicação do cartão.
    */
   @NotNull
   private String reason;

   public ClubTypeEnum getClubType() {
      return clubType;
   }

   public void setClubType(ClubTypeEnum clubType) {
      this.clubType = clubType;
   }

   public Long getCardOwner() {
      return cardOwner;
   }

   public void setCardOwner(Long cardOwner) {
      this.cardOwner = cardOwner;
   }

   public CardEnum getCardColor() {
      return cardColor;
   }

   public void setCardColor(CardEnum cardColor) {
      this.cardColor = cardColor;
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

   public String getReason() {
      return reason;
   }

   public void setReason(String reason) {
      this.reason = reason;
   }
}
