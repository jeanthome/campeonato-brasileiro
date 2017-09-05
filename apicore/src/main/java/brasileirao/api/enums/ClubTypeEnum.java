package brasileirao.api.enums;

/**
 * Define os tipos dos clubes nas partidas para facilitar edições nas mesmas.
 */
public enum ClubTypeEnum {

   /**
    * O clube mandante.
    */
   HOME_CLUB("HOMECLUB"),

   /**
    * O clube visitante.
    */
   VISITOR_CLUB("VISITORCLUB");

   /**
    * Descrição do tipo.
    */
   private String clubType;

   /**
    * Contrutor padrão.
    * @param clubType A descrição do tipo.
    */
   ClubTypeEnum(String clubType) {
      this.clubType = clubType;
   }

   public String getClubType() {
      return clubType;
   }

   public void setClubType(String clubType) {
      this.clubType = clubType;
   }
}
