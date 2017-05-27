package brasileirao.api.persistence;

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
 * Representa um gol em uma partida.
 */
@Entity
@Table(name = "GOAL")
public class Goal {

    /**
     * Id da entidade
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Dono do gol
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Player owner;

    /**
     * Minuto no qual o gol foi marcado
     */
    @Column(name = "MINUTE")
    @NotNull
    @Min(0)
    private Long minute;

    /**
     * Jogador que fez a assistência.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Player assist;

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Long getMinute() {
        return minute;
    }

    public void setMinute(Long minute) {
        this.minute = minute;
    }

    public Player getAssist() {
        return assist;
    }

    public void setAssist(Player assist) {
        this.assist = assist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
