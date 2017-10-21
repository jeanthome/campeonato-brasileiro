package brasileirao.api.enums;

import java.util.HashMap;
import java.util.Map;

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
	 * Lookup para obter a instância a partir do nome.
	 */
	private static Map<String, HalfEnum> lookup = new HashMap<>();

	/**
	 * Descricao do tempo.
	 */
	private String description;

	/**
	 * Construtor do enum.
	 * 
	 * @param description
	 *            A descricao do tempo.
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

	static {
		for (HalfEnum halfEnum : HalfEnum.values()) {
			lookup.put(halfEnum.name(), halfEnum);
		}
	}

	/**
	 * Obtém instância do Enum a partir da
	 * 
	 * @param name Nome no enum.
	 * @return
	 */
	public HalfEnum getByName(String name) {
		return lookup.get(name);
	}
}
