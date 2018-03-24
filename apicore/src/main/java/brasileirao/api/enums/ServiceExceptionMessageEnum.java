package brasileirao.api.enums;

/**
 * Enum de mensagens de erro das classes de serviço.
 */
public enum ServiceExceptionMessageEnum {

  /**
   * Jogador não encontrado.
   */
  PLAYER_NOT_FOUND("Jogador não encontrado."),

  /**
   * Partida não encontrada.
   */
  MATCH_NOT_FOUND("Partida não encontrada."),

  /**
   * Clube não encontrado.
   */
  CLUB_NOT_FOUND("Clube não encontrado."),

  /**
   * Técnico não encontrado.
   */
  COACH_NOT_FOUND("Técnico não encontrado."),

  /**
   * Escudo não encontrado.
   */
  CLUB_BADGE_NOT_FOUND("O escudo do clube não foi encontrado."),

  /**
   * Mensagem de erro para quando a imagem do jogador não for encontrada.
   */
  PLAYER_IMAGE_NOT_FOUND("A imagem do jogador não foi encontrada."),

  /**
   * Mensagem de erro para quando a imagem do técnico não for encontrada.
   */
  COACH_IMAGE_NOT_FOUND("A imagem do jogador não foi encontrada");

  /**
   * Mensagem a ser lançada junta com a exceção.
   */
  private String message;

  /**
   * Construtor padrão.
   *
   * @param message A mensagem de erro.
   */
  ServiceExceptionMessageEnum(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
