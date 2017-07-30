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
   MATCH_NOT_FOUND("Partida não encontrada.");

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
