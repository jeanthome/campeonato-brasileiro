package brasileirao.api.dto;

import brasileirao.api.enums.PositionEnum;
import brasileirao.api.persistence.Person;

import javax.validation.constraints.NotNull;

/**
 * DTO a ser usado na tela de cadastro de jogador.
 */
public class PlayerRegisterDto extends Person {

   /**
    * Posição do jogador
    */
   @NotNull
   private PositionEnum positionEnum;

   /**
    * Número da camisa do jogador
    */
   @NotNull
   private Long number;

   /**
    * Identificador do clube atual do jogador
    */
   private Long actualClubId;

   /**
    * Contrutor vazio, usado nos testes unitários.
    */
   public PlayerRegisterDto() {
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

   public Long getActualClubId() {
      return actualClubId;
   }

   public void setActualClubId(Long actualClubId) {
      this.actualClubId = actualClubId;
   }
}
