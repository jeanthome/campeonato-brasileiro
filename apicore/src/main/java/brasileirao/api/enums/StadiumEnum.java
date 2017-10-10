package brasileirao.api.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Enum que mapeia todos os estádios onde os jogos podem ocorrer.
 */
public enum StadiumEnum {

   /**
    * Arena Conda
    */
   ARENA_CONDA("Arena Condá"),

   /**
    * Arena Corinthians
    */
   ARENA_CORINTHIANS("Arena Corinthians"),

   /**
    * Arena da Baixada
    */
   ARENA_DA_BAIXADA("Arena da Baixada"),

   /**
    * Arena do Grêmio
    */
   ARENA_DO_GREMIO("Arena do Grêmio"),

   /**
    * Arena Palmeiras
    */
   ARENA_PALMEIRAS("Arena Palmeiras"),

   /**
    * Couto Pereira
    */
   COUTO_PEREIRA("Couto Pereira"),

   /**
    * Engenhão
    */
   ENGENHAO("Engenhão"),

   /**
    * Fonte Nova
    */
   FONTE_NOVA("Fonte Nova"),

   /**
    * Ilha do Retiro
    */
   ILHA_DO_RETIRO("Ilha do Retiro"),

   /**
    * Independência
    */
   INDEPENDENCIA("Independência"),

   /**
    * Maracanã
    */
   MARACANA("Maracanã"),

   /**
    * Mineirão
    */
   MINEIRAO("Mineirão"),

   /**
    * Miosés Lucarelli
    */
   MOISES_LUCARELLI("Moisés Lucarelli"),

   /**
    * Morumbi
    */
   MORUMBI("Morumbi"),

   /**
    * Raulino de Oliveira
    */
   RAULINO_DE_OLIVEIRA("Raulino de Oliveira"),

   /**
    * Ressacada
    */
   RESSACADA("Ressacada"),

   /**
    * São Januário
    */
   SAO_JANUARIO("São Januário"),

   /**
    * Serra Dourada
    */
   SERRA_DOURADA("Serra Dourada"),

   /**
    * Vila Belmiro
    */
   VILA_BELMIRO("Vila Belmiro"),

   /**
    * Ilha do Urubu.
    */
   ILHA_DO_URUBU("Ilha do Urubu"),

   /**
    * Pacaembu.
    */
   PACAEMBU("Pacaembu"),

   /**
    * Estádio Olímpico.
    */
   ESTADIO_OLIMPICO("Estádio Olímpico"),

   /**
    * Barradão.
    */
   BARRADAO("Barradão"),

   /**
    * Arena de Pernambuco
    */
   ARENA_DE_PERNAMBUCO("Arena de Pernambuco");


   /**
    * O nome do estádio.
    */
   private String name;

   /**
    * Construtor do Enum.
    *
    * @param name O nome do estádio.
    */
   StadiumEnum(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   /**
    * Retorna a lista dos estádios onde os jogos podem ser realizados.
    *
    * @return Lista com as informações do estádios em StadiumEnum.
    */
   public static List<HashMap<String, String>> getStadiumEnumList() {
      final List<HashMap<String, String>> stadiumEnumList = new ArrayList<>();

      for (StadiumEnum stadiumEnum : StadiumEnum.values()) {
         final HashMap<String, String> hashMap = new HashMap<>();
         hashMap.put("value", stadiumEnum.name());
         hashMap.put("label", stadiumEnum.getName());
         stadiumEnumList.add(hashMap);
      }
      return stadiumEnumList;
   }
}
