package brasileirao.api.enums;

/**
 * Mensagens de erro a serem lançadas em validações.
 */
public enum ValidationExceptionMessageEnum {

  /**
   * Número inválido.
   */
  INVALID_NUMBER,

  /**
   * Rodada inválida
   */
  INVALID_ROUND_NUMBER,

  /**
   * Id do clube inválido.
   */
  INVALID_CLUB_ID,

  /**
   * Id do jogador está errado.
   */
  INVALID_PLAYER_ID,

  /**
   * Id do técnico está errado.
   */
  INVALID_COACH_ID,

  /**
   * Id da partida está inválido
   */
  INVALID_MATCH_ID;

  ValidationExceptionMessageEnum() {}
}
