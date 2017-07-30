package brasileirao.web.helper;

import brasileirao.api.enums.ValidationExceptionMessageEnum;
import brasileirao.api.exception.ValidationException;
import brasileirao.api.helper.ValidationHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para conversões auxiliares.
 */
public class ConverterHelper {

   /**
    * Converte uma String com ids, separados por (;), em um array de ids do tipo Long.
    *
    * @param stringWithIds String com os ids separados por (;)
    * @return Lista de Long com os valores convertidos.
    * @throws ValidationException Exceção de validação.
    */
   public static final List<Long> convertStringWithIdsToList(String stringWithIds) throws ValidationException {

      final List<Long> returnList = new ArrayList<>();

      if (stringWithIds != null) {

         final String[] array = stringWithIds.split(";");
         for (int i = 0; i < array.length; i++) {
            if (ValidationHelper.isNumber(array[i])) {
               returnList.add(convertStringToLong(array[i]));
            } else {
               throw new ValidationException(ValidationExceptionMessageEnum.INVALID_NUMBER.
                       getMessage());
            }
         }
      }
      return returnList;
   }

   /**
    * Converte uma String com número em um número do tipo Long.
    *
    * @param number String com o número a ser convertido.
    * @return Long com o número convertido.
    */
   public static final Long convertStringToLong(String number) {

      //TODO Adicionar regex para validar se é número ou não e lancár exceção.
      return Long.valueOf(number);

   }
}
