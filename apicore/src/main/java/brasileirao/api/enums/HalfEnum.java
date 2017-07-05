package brasileirao.api.enums;

/**
 * Define os tempos em que os eventos(Gol, Cartao, Substituiçao) podem ocorrer.
 */
public enum HalfEnum {

   /**
    * Primeiro Tempo.
    */
   FIRST_HALF("1° Tempo"),

   /**
    * Segundo Tempo.
    */
   SECOND_HALF("2° Tempo");

   /**
    * Descricao do tempo.
    */
   private String description;

   /**
    * Construtor do enum.
    * @param description A descricao do tempo.
    */
   HalfEnum(String description) {
      this.description = description;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }
}
