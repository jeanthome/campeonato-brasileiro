package brasileirao.api.exception;

/**
 * Classe de exceções a serem lançadas pelas classes de serviços.
 */
public class ServiceException extends Exception {

   /**
    * Construtor padrão.
    *
    * @param message Mensagem que descreve o motivo da exceção ter sido lançada.
    */
   public ServiceException(String message) {
      super(message);
   }
}
