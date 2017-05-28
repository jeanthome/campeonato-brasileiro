package brasileirao.api.converter;

import brasileirao.api.dto.ClubDto;
import brasileirao.api.dto.CoachDto;
import brasileirao.api.persistence.Club;
import brasileirao.api.persistence.Coach;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class ConvertHelper {

   public static Period convertDateToPeriod(Date date){
      final LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      return Period.between(localDate, LocalDate.now());
   }

   /**
    * Converte uma instância de <i>Coach</i> ao seu respectivo DTO.
    *
    * @param coach Instância da classe <i>Coach</i>, que será convertida em DTO.
    * @return Instância de <i>ClubDto</i>
    */
   public static CoachDto convertCoachToDto(Coach coach) {
      final CoachDto coachDto = modelMapper.map(coach, CoachDto.class);

      /*Obtem e setta a idade do técnico.*/
      final Date date = coach.getBirthDate();
      coachDto.setAge(ConvertHelper.convertDateToPeriod(date).getYears());
      return coachDto;
   }


   /**
    * Converte uma instância de <i>Club</i> ao seu respectivo DTO.
    *
    * @param club Instância da classe <i>Club</i>, que será convertida em DTO.
    * @return Instância de <i>ClubDto</i>
    */
   public static ClubDto convertClubToDto(Club club) {
      final ClubDto clubDto = modelMapper.map(club, ClubDto.class);
      return clubDto;
   }

}
