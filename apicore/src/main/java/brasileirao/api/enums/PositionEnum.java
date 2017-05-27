package brasileirao.api.enums;

/**
 * Define as posicões possíveis para um jogador.
 * */
public enum PositionEnum {

    /**
     * Goleiro
     * */
    GOALKEEPER("Goleiro"),

    /**
     * Zagueiro
     * */
    DEFENDER("Zagueiro"),

    /**
     * Lateral Esquerdo
     * */
    LEFT_BACK("Lateral Esquerdo"),

    /**Lateral Direito*/
    RIGHT_BACK("Lateral Direito"),

    /**
     * Volante
     * */
    DEFENSIVE_MIDFIELDER("Volante"),

    /**
     * Meia Central
     * */
    MIDFIELDER("Meia Central"),

    /**
     * Atacante
     * */
    STRIKER("Atacante");

    /**
     * Nome da posicão.
     * */
    private String positionName;

    /**
     * Cria o item do ENUM representa uma posicão de jogador.
     * @param positionName O nome da posicão.
     * */
    PositionEnum(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionName() {
        return positionName;
    }

}
