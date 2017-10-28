package brasileirao.api.dto;

import brasileirao.api.enums.CardEnum;
import brasileirao.api.enums.HalfEnum;

/**
 * DTO para a representação de cartão.
 */
public class CardDto {

  /**
   * Jogador que recebeu o cartão.
   */
  private PlayerMinDto cardOwner;

  /**
   * Minuto no qual o cartao foi dado.
   */
  private Long minute;

  /**
   * O tempo no qual o cartao foi dado.
   */
  private HalfEnum half;

  /**
   * Cor do cartão.
   */
  private CardEnum cardColor;

  /**
   * O motivo pelo qual o jogador recebeu o cartão.
   */
  private String reason;

  public PlayerMinDto getCardOwner() {
    return cardOwner;
  }

  public void setCardOwner(PlayerMinDto cardOwner) {
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
