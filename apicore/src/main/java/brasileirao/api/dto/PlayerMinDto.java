package brasileirao.api.dto;

import org.springframework.hateoas.ResourceSupport;

public class PlayerMinDto extends ResourceSupport {

   /**
    * Identificador do jogador.
    */
   private Long identificator;
   /**
    * Nome de exibição do jogador.
    */
   private String displayName;

   /**
    * Posição do jogador (abreviação)
    */
   private String positionAbbreviation;

   /***
    * Número da camisa do Jogador.
    */
   private Long number;

   public Long getIdentificator() {
      return identificator;
   }

   public void setIdentificator(Long identificator) {
      this.identificator = identificator;
   }

   public String getDisplayName() {
      return displayName;
   }

   public void setDisplayName(String displayName) {
      this.displayName = displayName;
   }

   public String getPositionAbbreviation() {
      return positionAbbreviation;
   }

   public void setPositionAbbreviation(String positionAbbreviation) {
      this.positionAbbreviation = positionAbbreviation;
   }

   public Long getNumber() {
      return number;
   }

   public void setNumber(Long number) {
      this.number = number;
   }
}
