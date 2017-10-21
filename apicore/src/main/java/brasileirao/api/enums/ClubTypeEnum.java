package brasileirao.api.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Define os tipos dos clubes nas partidas para facilitar edições nas mesmas.
 */
public enum ClubTypeEnum {

	/**
	 * O clube mandante.
	 */
	HOME_CLUB,

	/**
	 * O clube visitante.
	 */
	VISITOR_CLUB;

	/**
	 * Lookup para obter a instância a partir da descrição.
	 */
	private static Map<String, ClubTypeEnum> lookup = new HashMap<>();

	/**
	 * Construtor padrão.
	 */
	ClubTypeEnum() {
	}

	static {
		for (ClubTypeEnum clubTypeEnum : ClubTypeEnum.values()) {
			lookup.put(clubTypeEnum.name(), clubTypeEnum);
		}
	}

   /**
    * Obtém instância do Enum a partir da
    *
    * @param name Nome no enum.
    * @return
    */
   public ClubTypeEnum getByName(String name) {
      return lookup.get(name);
   }
}
