package brasileirao.api.persistence;

import brasileirao.api.enums.CardEnum;
import brasileirao.api.enums.HalfEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Representa um cartão dado a um jogador durante uma partida.
 */
@Entity
@Table(name = "CARD")
public class Card {

   /**
    * Id da entidade
    */
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   /**
    * Jogador que recebeu o cartão.
    */
   @NotNull
   @ManyToOne(fetch = FetchType.LAZY)
   private PlayerInMatch cardOwner;

   /**
    * Minuto no qual o cartao foi dado.
    */
   @Min(0)
   @NotNull
   @Column(name = "MINUTE")
   private Long minute;

   /**
    * O tempo no qual o cartao foi dado.
    */
   @NotNull
   @Column(name = "HALF")
   private HalfEnum half;

   /**
    * Cor do cartão.
    */
   @NotNull
   @Column(name = "CARD")
   private CardEnum cardColor;

   /**
    * O motivo pelo qual o jogador recebeu o cartão.
    */
   @NotNull
   @Column(name = "REASON", length = 350)
   private String reason;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public PlayerInMatch getCardOwner() {
      return cardOwner;
   }

   public void setCardOwner(PlayerInMatch cardOwner) {
      this.cardOwner = cardOwner;
   }

   public Long getMinute() {
      return minute;
   }

   public void setMinute(Long minute) {
      this.minute = minute;
   }

   public HalfEnum getHalf() {
      return half;
   }

   public void setHalf(HalfEnum half) {
      this.half = half;
   }

   public CardEnum getCardColor() {
      return cardColor;
   }

   public void setCardColor(CardEnum cardColor) {
      this.cardColor = cardColor;
   }

   public String getReason() {
      return reason;
   }

   public void setReason(String reason) {
      this.reason = reason;
   }
}
