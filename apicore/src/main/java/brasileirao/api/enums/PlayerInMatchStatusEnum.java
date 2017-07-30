package brasileirao.api.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Status de um jogador em uma partida.
 */
public enum PlayerInMatchStatusEnum {


   /**
    * Jogador titular.
    */
   STARTING(1L, "Starting"),

   /**
    * Jogador reserva.
    */
   SUBSTITUTE(2L, "Substitute");

   /**
    * Mapa para recuperar Enum pelo Id.
    */
   private static Map<Long, PlayerInMatchStatusEnum> lookup = new HashMap<>();

   /**
    * Id do status.
    */
   private Long id;

   /**
    * Descrição do status.
    */
   private String description;

   /**
    * Construtor padrão.
    *
    * @param id          Id do status no Enum.
    * @param description Descrição do Status no Enum.
    */
   PlayerInMatchStatusEnum(Long id, String description) {
      this.id = id;
      this.description = description;
   }

   static {
      for (PlayerInMatchStatusEnum statusEnum : PlayerInMatchStatusEnum.values()) {
         lookup.put(statusEnum.getId(), statusEnum);
      }
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public static PlayerInMatchStatusEnum getById(Long id) {
      return lookup.get(id);
   }


}
