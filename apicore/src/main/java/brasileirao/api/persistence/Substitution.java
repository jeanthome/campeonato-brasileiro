package brasileirao.api.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import brasileirao.api.enums.HalfEnum;

/**
 * Representa uma substituição feita durante um jogo.
 */
@Entity
public class Substitution {

  /**
   * ID do jogador. É a P.K. da entidade.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  /**
   * O jogador que foi substituido.
   */
  @NotNull
  @ManyToOne
  @JoinColumn(name = "PLAYER_WHO_LEAVES")
  private PlayerInMatch playerWhoLeaves;

  /**
   * O jogador que entrou na partida.
   */
  @NotNull
  @ManyToOne
  @JoinColumn(name = "PLAYER_WHO_ENTERS")
  private PlayerInMatch playerWhoEnters;

  /**
   * O minuto no qual a substituição ocorreu.
   */
  @NotNull
  @Column(name = "MINUTE")
  @Min(0)
  private Long minute;

  /**
   * O tempo em que a substituição ocorreu.
   */
  @NotNull
  @Column(name = "HALF")
  private HalfEnum half;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PlayerInMatch getPlayerWhoLeaves() {
    return playerWhoLeaves;
  }

  public void setPlayerWhoLeaves(PlayerInMatch playerWhoLeaves) {
    this.playerWhoLeaves = playerWhoLeaves;
  }

  public PlayerInMatch getPlayerWhoEnters() {
    return playerWhoEnters;
  }

  public void setPlayerWhoEnters(PlayerInMatch playerWhoEnters) {
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
