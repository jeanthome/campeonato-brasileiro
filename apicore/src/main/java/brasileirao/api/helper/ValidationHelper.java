package brasileirao.api.helper;

/**
 * Classe para validacões pontuais.
 */
public final class ValidationHelper {

   /**
    * Regex para validar números.
    */
   private static final String NUMBER = "\\d+";

   /**
    * Contrutor privado
    */
   private ValidationHelper() {
   }

   /**
    * Verifica se uma string é vazia ou nula.
    *
    * @param value String a ser validada.
    * @return True, se e somente se a string for vazia ou nula. false caso contrário.
    */
   public static boolean isEmptyOrVoid(final String value) {

      if (value == null) {
         return true;
      } else if (value.isEmpty()) {
         return true;
      }
      return false;
   }

   /**
    * Verifica se uma dada String representa um número/
    *
    * @param number A string a ser verificada.
    * @return true, se e somente se a string rerpesenta um número. false, caso contrário.
    */
   public static boolean isNumber(final String number) {
      if (isEmptyOrVoid(number)) {
         return false;
      } else if (number.matches(NUMBER)) {
         return true;
      }
      return false;
   }
}
