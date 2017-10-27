package brasileirao.api.dto;

/**
 * DTO para a representação mínima de um jogador.
 */
public class PlayerMinDto {

   /**
    * Identificador do jogador.
    */
   private Long id;
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

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
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
