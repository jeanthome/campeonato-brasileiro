package brasileirao.api.dto;

import org.springframework.hateoas.ResourceSupport;

import java.util.Map;

public class PlayerDto extends ResourceSupport {

   /**
    * Id do jogador.
    */
   private Long id;

   /**
    * Nome de exibição do jogador.
    */
   private String displayName;

   /**
    * Posição do jogador. (Nome e abreviação da posição)
    */
   private Map<String, String> position;

   /***
    * Número da camisa do Jogador.
    */
   private Long number;

   public void setId(Long id) {
      this.id = id;
   }

   public String getDisplayName() {
      return displayName;
   }

   public void setDisplayName(String displayName) {
      this.displayName = displayName;
   }

   public Map<String, String> getPosition() {
      return position;
   }

   public void setPosition(Map<String, String> position) {
      this.position = position;
   }

   public Long getNumber() {
      return number;
   }

   public void setNumber(Long number) {
      this.number = number;
   }
}
