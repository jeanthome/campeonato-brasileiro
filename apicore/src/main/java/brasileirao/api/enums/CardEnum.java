package brasileirao.api.enums;

/**
 * Define as cores do cartões que um jogador pode receber durante uma partida.
 * */
public enum CardEnum {

    /**
     * Cartão Amarelo
     * */
    YELLOW("Cartão Amarelo"),

    /**
     * Cartão Vermelho
     * */
    RED("Cartão Vermelho");

    /**
     * Cor do cartão
     * */
    private String color;

    /**
     * Cria instancia do Enum.
     * @param color A cor do cartão.
     * */
    CardEnum(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
