package brasileirao.api.enums;

/**
 * Mensagens de erro a serem lançadas em validações.
 */
public enum ValidationExceptionMessageEnum {

   /**
    * Número inválido.
    */
   INVALID_NUMBER("Número inválido");

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
