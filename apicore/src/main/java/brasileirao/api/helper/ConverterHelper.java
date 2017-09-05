package brasileirao.api.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe para conversões auxiliares.
 */
public final class ConverterHelper {

   /**
    * Construtor privado.
    */
   private ConverterHelper() {
   }

   /**
    * Converte uma String com número em um número do tipo Long.
    *
    * @param number String com o número a ser convertido.
    * @return Long com o número convertido.
    */
   public static Long convertStringToLong(String number) {

      //TODO Adicionar regex para validar se é número ou não e lancár exceção.
      return Long.valueOf(number);
   }
}
