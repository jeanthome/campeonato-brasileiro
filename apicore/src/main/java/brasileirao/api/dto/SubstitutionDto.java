package brasileirao.api.dto;

import brasileirao.api.enums.HalfEnum;

/**
 * DTO para a representação de uma substituição em uma partida.
 */
public class SubstitutionDto {

  /**
   * O jogador que foi substituido.
   */
  private PlayerMinDto playerWhoLeaves;

  /**
   * O jogador que entrou na partida.
   */
  private PlayerMinDto playerWhoEnters;

  /**
   * O minuto no qual a substituicao ocorreu.
   */
  private Long minute;

  /**
   * O tempo em que a substituicao ocorreu.
   */
  private HalfEnum half;

  public PlayerMinDto getPlayerWhoLeaves() {
    return playerWhoLeaves;
  }

  public void setPlayerWhoLeaves(PlayerMinDto playerWhoLeaves) {
    this.playerWhoLeaves = playerWhoLeaves;
  }

  public PlayerMinDto getPlayerWhoEnters() {
    return playerWhoEnters;
  }

  public void setPlayerWhoEnters(PlayerMinDto playerWhoEnters) {
    this.playerWhoEnters = playerWhoEnters;
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
}
