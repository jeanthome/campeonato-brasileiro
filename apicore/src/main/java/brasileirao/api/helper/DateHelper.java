package brasileirao.api.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Classe utilitaria a ser usada para conversoes (DTO, Date).
 */
public final class DateHelper {

   /**
    * Construtor privado.
    */
   private DateHelper() {
   }

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

   /**
    * Converte uma String no formato dd/MM/yyyy HH:mm para um objeto da classe Date.
    *
    * @param date String com a data a ser convertida.
    * @return Date com a data convertida.
    * @throws ParseException Lançada caso ocorra algum erro durante a conversão.
    */
   public static Date convertStringToDate(String date) throws ParseException {
      final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

      return format.parse(date);
   }

   /**
    * Retorna a data atual.
    * @return Date com a data atual.
    */
   public static Date now() {
      return Calendar.getInstance().getTime();
   }

   /**
    * Converte um objeto Date em uma String representativa da data pro formato (DAY dd/MM/yyy).
    *
    * @param date Data a ser convertida.
    * @return String com a data convertida.
    */
   public static String getFormattedDate(Date date) {
      String formatted = "";
      String dayWeek = "";
      final GregorianCalendar calendar = new GregorianCalendar();

      final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
      calendar.setTime(date);

      switch (calendar.get(Calendar.DAY_OF_WEEK)) {
         case Calendar.SUNDAY:
            dayWeek = "DOM";
            break;
         case Calendar.MONDAY:
            dayWeek = "SEG";
            break;
         case Calendar.TUESDAY:
            dayWeek = "TER";
            break;
         case Calendar.WEDNESDAY:
            dayWeek = "QUA";
            break;
         case Calendar.THURSDAY:
            dayWeek = "QUI";
            break;
         case Calendar.FRIDAY:
            dayWeek = "SEX";
            break;
         case Calendar.SATURDAY:
            dayWeek = "SAB";
            break;
         default:
            dayWeek = "-";

      }

      formatted = dayWeek + " " + format.format(date);

      return formatted;
   }

   /**
    * Converte um objeto Date em uma string com o horário formatado (HH:mm).
    *
    * @param date Data a ser convertida.
    * @return String com o horário formatado.
    */
   public static String getFormattedHour(Date date) {

      final Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);

      final int hour = calendar.get(Calendar.HOUR_OF_DAY);
      final int minute = calendar.get(Calendar.MINUTE);

      return String.format("%02d:%02d", hour, minute);
   }
}
