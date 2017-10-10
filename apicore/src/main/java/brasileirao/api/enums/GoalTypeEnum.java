package brasileirao.api.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Tipos de gols
 */
public enum GoalTypeEnum {

   /**
    * Gol normal.
    */
   NORMAL("Normal"),

   /**
    * Gol de pênalti
    */
   PENALTY("Pênalti"),

   /**
    * Gol contra.
    */
   OWN_GOAL("Gol contra"),

   /**
    * Gol de falta.
    */
   FOUL("Gol de falta");


   /**
    * Descrição do tipo de gol.
    */
   private String description;

   /**
    * Construtor padrão.
    *
    * @param description A descrição do tipo de gol.
    */
   GoalTypeEnum(String description) {
      this.description = description;
   }

   public String getDescription() {
      return description;
   }

   /**
    * Obtém uma lista com os tipos de gols.
    *
    * @return Lista com os tipos de gols.
    */
   public static List<HashMap<String, String>> getGoalTypeEnumList() {
      final List<HashMap<String, String>> goalsTypeEnumList = new ArrayList<>();

      for (GoalTypeEnum goalTypeEnum : GoalTypeEnum.values()) {
         final HashMap<String, String> hashMap = new HashMap<>();
         hashMap.put("value", goalTypeEnum.name());
         hashMap.put("label", goalTypeEnum.getDescription());
         goalsTypeEnumList.add(hashMap);
      }
      return goalsTypeEnumList;
   }
}
