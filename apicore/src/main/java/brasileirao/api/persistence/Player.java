package brasileirao.api.persistence;

import brasileirao.api.enums.PositionEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Entidade que representa um jogador.
 */
@Entity
@Table(name = "PLAYER")
public class Player extends Person {

   /**
    * Posição do jogador
    */
   @NotNull
   @Column(name = "POSITION")
   private PositionEnum positionEnum;

   /**
    * Número da camisa do jogador
    */
   @NotNull
   @Column(name = "NUMBER")

   private Long number;

   /**
    * Club atual do jogador
    */
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "CLUB_ID")
   private Club actualClub;

   /**
    * Contrutor vazio, usado nos testes unitários.
    */
   public Player() {
   }

   public PositionEnum getPositionEnum() {
      return positionEnum;
   }

   public void setPositionEnum(PositionEnum positionEnum) {
      this.positionEnum = positionEnum;
   }

   public Long getNumber() {
      return number;
   }

   public void setNumber(Long number) {
      this.number = number;
   }

   public Club getActualClub() {
      return actualClub;
   }

   public void setActualClub(Club actualClub) {
      this.actualClub = actualClub;
   }

}
