package brasileirao.api.persistence;

import brasileirao.api.enums.CardEnum;

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
    private Player owner;

    /**
     * Minuto no qual o gol foi marcado
     */
    @Column(name = "MINUTE")
    @NotNull
    @Min(0)
    private Long minute;

    /**
     * Cor do cartão.
     */
    @Column(name = "CARD")
    @NotNull
    private CardEnum color;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public CardEnum getColor() {
        return color;
    }

    public void setColor(CardEnum color) {
        this.color = color;
    }
}
