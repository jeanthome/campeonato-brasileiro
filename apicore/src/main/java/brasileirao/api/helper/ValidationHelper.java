package brasileirao.api.helper;

import brasileirao.api.exception.ServiceException;
import brasileirao.api.exception.ValidationException;

/**
 * Classe para validacões pontuais.
 */
public final class ValidationHelper {

   /**
    * Regex para validar números.
    */
   private static final String NUMBER = "\\d+";

   /**
    * Número máximo de rodadas.
    */
   private static final int MAX_ROUND = 38;

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

   /**
    * Verifica se uma dada String representa uma rodada válida.
    *
    * @param roundNumber A string a ser validada.
    * @return true, se e somente se a string rerpesenta um número de rodada. false, caso contrário.
    */
   public static boolean isRoundNumber(final String roundNumber) throws ValidationException{

      if (isNumber(roundNumber)) {

         final Long converted = ConverterHelper.convertStringToLong(roundNumber);

         if (converted > 0 && converted <= MAX_ROUND) {
            return true;
         }
      }
      return false;
   }

   /**
    * Verifica se um dado Long representa uma rodada válida.
    *
    * @param roundNumber A string a ser validada.
    * @return true, se e somente se a string rerpesenta um número de rodada. false, caso contrário.
    */
   public static boolean isRoundNumber(final Long roundNumber) {

      if (roundNumber > 0 && roundNumber <= MAX_ROUND) {
         return true;
      }
      return false;
   }
}
