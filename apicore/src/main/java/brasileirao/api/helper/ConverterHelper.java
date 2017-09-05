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

   /**
    * Converte uma String no formato dd/MM/yyyy HH:mm para um objeto da classe Date.
    *
    * @param date String com a data a ser convertida.
    * @return Date com a data convertida.
    * @throws ParseException Lançada caso ocorra algum erro durante a conversão.
    */
   public static Date convertStringToDate(String date) throws ParseException{
      final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
      return format.parse(date);
   }
}
