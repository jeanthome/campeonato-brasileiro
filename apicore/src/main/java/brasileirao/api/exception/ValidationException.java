package brasileirao.api.exception;

/**
 * Classe de exceções lançadas durante as validacões.
 */
public class ValidationException extends Exception {

   /**
    * Construtor padrão.
    *
    * @param message Mensagem que descreve o motivo da exceção ter sido lançada.
    */
   public ValidationException(String message) {
      super(message);
   }
}
