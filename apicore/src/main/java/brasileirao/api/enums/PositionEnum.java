package brasileirao.api.enums;

/**
 * Define as posicões possíveis para um jogador.
 */
public enum PositionEnum {

   /**
    * Goleiro
    */
   GOALKEEPER(1L, "Goleiro", "Gol"),

   /**
    * Zagueiro Esquerdo
    */
   LEFT_DEFENDER(4L, "Zagueiro Esquerdo", "ZAE"),

   /**
    * Zagueiro Direito
    */
   RIGHT_DEFENDER(3L, "Zagueiro Direito", "ZAD"),

   /**
    * Lateral Esquerdo
    */
   LEFT_BACK(5L, "Lateral Esquerdo", "LAE"),

   /**
    * Lateral Direito
    */
   RIGHT_BACK(2L, "Lateral Direito", "LAD"),

   /**
    * Volante
    */
   DEFENSIVE_MIDFIELDER(6L, "Volante", "VOL"),

   /**
    * Meia Central
    */
   MIDFIELDER(7L, "Meia Central", "MEC"),

   /**
    * Atacante
    */
   STRIKER(8L, "Atacante", "ATA");

   /**
    * Valor usado para Players por posicões
    */
   private Long order;
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
    * @param order A ordem da posicão na hora de ordenar.
    * @param positionName O nome da posicão.
    * @param abbreviation A abreviação da posição.
    */
   PositionEnum(Long order, String positionName, String abbreviation) {
      this.order = order;
      this.positionName = positionName;
      this.abbreviation = abbreviation;
   }

   public String getPositionName() {
      return positionName;
   }

   public String getAbbreviation() {
      return abbreviation;
   }

   public Long getOrder() {
      return order;
   }
}
