package brasileirao.api.enums;

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
    * @param description
    */
   GoalTypeEnum(String description) {
      this.description = description;
   }

   public String getDescription() {
      return description;
   }
}
