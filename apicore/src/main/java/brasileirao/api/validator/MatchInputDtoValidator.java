package brasileirao.api.validator;

import brasileirao.api.dto.input.MatchInputDto;
import brasileirao.api.helper.ValidationHelper;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Classe para validar {@link MatchInputDto};
 */
public class MatchInputDtoValidator implements Validator {

   @Override
   public boolean supports(Class aClass) {
      return MatchInputDto.class.equals(aClass);
   }

   @Override
   public void validate(Object target, Errors errors) {

      final MatchInputDto matchInputDto = (MatchInputDto) target;

      if (!ValidationHelper.isRoundNumber(matchInputDto.getRoundNumber())) {
         errors.reject("roundNumber", "Rodada inválida.");
      }
   }
}
