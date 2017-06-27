package brasileirao.api.helper;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

/**
 * Classe utilitaria a ser usada para conversoes (DTO, Date).
 */
public class DateHelper {

   /**
    * Converte um <i>Date</i> (usado em birthDate ) para  <i>Period</i>. Isso facilita o calculo da
    * idade de um <i>Person</i>.
    *
    * @param date Atributo a ser convertido.
    * @return Instância de <i>Period</i> com as informacões separadas(year, month, day etc...)
    */
   public static Period convertDateToPeriod(Date date) {
      final LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      return Period.between(localDate, LocalDate.now());
   }
}
