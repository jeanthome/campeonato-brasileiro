package brasileirao.api.enums;

/**
 * Define as posicões possíveis para um jogador.
 */
public enum PositionEnum {

   /**
    * Goleiro
    */
   GOALKEEPER("Goleiro", "Gol"),

   /**
    * Zagueiro Esquerdo
    */
   LEFT_DEFENDER("Zagueiro Esquerdo", "ZAE"),

   /**
    * Zagueiro Direito
    */
   RIGHT_DEFENDER("Zagueiro Direito", "ZAD"),

   /**
    * Lateral Esquerdo
    */
   LEFT_BACK("Lateral Esquerdo", "LAE"),

   /**
    * Lateral Direito
    */
   RIGHT_BACK("Lateral Direito", "LAD"),

   /**
    * Volante
    */
   DEFENSIVE_MIDFIELDER("Volante", "VOL"),

   /**
    * Meia Central
    */
   MIDFIELDER("Meia Central", "MEC"),

   /**
    * Atacante
    */
   STRIKER("Atacante", "ATA");

   /**
    * Nome da posicão.
    */
   private String positionName;

   /***
    * Abreviação da posição.
    */
   private String abbreviation;

   /**
    * Cria o item do ENUM representa uma posicão de jogador.
    *
    * @param positionName O nome da posicão.
    * @param abbreviation A abreviação da posição.
    */
   PositionEnum(String positionName, String abbreviation) {
      this.positionName = positionName;
      this.abbreviation = abbreviation;
   }

   public String getPositionName() {
      return positionName;
   }

   public String getAbbreviation() {
      return abbreviation;
   }
}
