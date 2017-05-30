package brasileirao.api.converter;

import brasileirao.api.dto.ClubDto;
import brasileirao.api.dto.CoachDto;
import brasileirao.api.dto.PlayerDto;
import brasileirao.api.persistence.Club;
import brasileirao.api.persistence.Coach;
import brasileirao.api.persistence.Player;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/***
 * Faz algumas conversões pontuais.
 */
public class ConvertHelper {

   /***
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
    * Converte uma instância de <i>Coach</i> ao seu respectivo DTO.
    *
    * @param coach Instância da classe <i>Coach</i>, que será convertida em DTO.
    * @return Instância de <i>ClubDto</i>
    */
   public static CoachDto convertCoachToDto(Coach coach) {
      final ModelMapper modelMapper = new ModelMapper();
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
      final ModelMapper modelMapper = new ModelMapper();
      final ClubDto clubDto = modelMapper.map(club, ClubDto.class);
      return clubDto;
   }

   /**
    * Converte uma instância de {@link Player} ao seu respectivo DTO.
    *
    * @param player Instância da classe {@link Player}, que será convertida em DTO.
    * @return Instância de {@link PlayerDto}
    */
   public static PlayerDto convertPlayerToDto(Player player) {
      final ModelMapper modelMapper = new ModelMapper();
      final PlayerDto playerDto = modelMapper.map(player, PlayerDto.class);

      final Map<String, String> position = new HashMap<>();
      position.put("name", player.getPositionEnum().getPositionName());
      position.put("abbreviation", player.getPositionEnum().getAbbreviation());
      playerDto.setPosition(position);

      return playerDto;
   }
}
