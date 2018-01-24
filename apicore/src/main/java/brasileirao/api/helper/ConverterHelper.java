package brasileirao.api.helper;

import brasileirao.api.enums.ValidationExceptionMessageEnum;
import brasileirao.api.exception.ValidationException;

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
  public static Long convertStringToLong(String number) throws ValidationException {

    if (!ValidationHelper.isNumber(number)) {
      throw new ValidationException(ValidationExceptionMessageEnum.INVALID_NUMBER.getMessage());
    }

    return Long.valueOf(number);
  }
}
