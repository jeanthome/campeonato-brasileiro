package brasileirao.api.enums;

/**
 * Mensagens de erro a serem lançadas em validações.
 */
public enum ValidationExceptionMessageEnum {

  /**
   * Número inválido.
   */
  INVALID_NUMBER("Número inválido"),

  /**
   * Rodada inválida
   */
  INVALID_ROUND_NUMBER("Rodada inválida"),

  /**
   * Id do clube inválido.
   */
  INVALID_CLUB_ID("O Id do clube é inválido"),

  /**
   * Mensagem de erro para quando o id do jogador está errado.
   */
  INVALID_PLAYER_ID("O Id do jogador é inválido.");

  /**
   * Mensagem a ser lançada junta com a exceção.
   */
  private String message;

  /**
   * Construtor padrão.
   *
   * @param message A mensagem de erro.
   */
  ValidationExceptionMessageEnum(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
