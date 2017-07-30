package brasileirao.api.persistence;

import brasileirao.api.enums.PositionEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Classe que representa um jogador na partida. Usada para manter a consistência em casos em que um
 * jogador muda de clube/número.
 */
@Entity
@Table(name = "PLAYER_IN_MATCH")
public class PlayerInMatch implements Comparable<PlayerInMatch> {

   /**
    * Id do jogador. P.K. da tabela.
    */
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   /**
    * Perfil completo do jogador.
    */
   @ManyToOne
   @JoinColumn(name = "SOURCE_PLAYER_ID")
   private Player sourcePlayer;

   /**
    * Número da camisa do jogador na partida.
    */
   @NotNull
   @Column(name = "NUMBER")
   private Long number;

   /**
    * Nome de exibição do jogador.
    */
   @NotNull
   @Column(name = "DISPLAY_NAME")
   private String displayName;

   /**
    * Posição do jogador
    */
   @NotNull
   @Column(name = "POSITION")
   private PositionEnum positionEnum;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Player getSourcePlayer() {
      return sourcePlayer;
   }

   public void setSourcePlayer(Player sourcePlayer) {
      this.sourcePlayer = sourcePlayer;
   }

   public Long getNumber() {
      return number;
   }

   public void setNumber(Long number) {
      this.number = number;
   }

   public String getDisplayName() {
      return displayName;
   }

   public void setDisplayName(String displayName) {
      this.displayName = displayName;
   }

   public PositionEnum getPositionEnum() {
      return positionEnum;
   }

   public void setPositionEnum(PositionEnum positionEnum) {
      this.positionEnum = positionEnum;
   }

   @Override
   public int compareTo(PlayerInMatch playerInMatch) {
      return this.getPositionEnum().getOrder().compareTo(playerInMatch.getPositionEnum()
              .getOrder());
   }
}

