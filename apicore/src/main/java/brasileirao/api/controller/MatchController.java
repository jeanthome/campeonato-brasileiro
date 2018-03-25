package brasileirao.api.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import brasileirao.api.dto.GoalDto;
import brasileirao.api.dto.MatchDto;
import brasileirao.api.dto.MatchMinDto;
import brasileirao.api.dto.input.CardInputDto;
import brasileirao.api.dto.input.GoalInputDto;
import brasileirao.api.dto.input.MatchInputDto;
import brasileirao.api.dto.input.SubstitutionInputDto;
import brasileirao.api.enums.GoalTypeEnum;
import brasileirao.api.enums.StadiumEnum;
import brasileirao.api.enums.ValidationExceptionMessageEnum;
import brasileirao.api.exception.ServiceException;
import brasileirao.api.exception.ValidationException;
import brasileirao.api.helper.ConverterHelper;
import brasileirao.api.helper.ValidationHelper;
import brasileirao.api.persistence.Match;
import brasileirao.api.service.MatchService;
import brasileirao.api.validator.MatchInputDtoValidator;

/**
 * Lida com as requisicões referentes à entidade {@link Match}
 */
@CrossOrigin
@RestController
@RequestMapping("/matches")
public class MatchController {

  /**
   * Instância da classe de serviços da entidade {@link brasileirao.api.persistence.Match}
   */
  @Autowired
  private MatchService matchService;

  /**
   * Obtém uma partida a partir de seu identificador único.
   *
   * @param id O identificador da partida.
   * @return Instância de {@link MatchDto} caso a partida seja encontrada. <i>null</i> caso
   *         contrário
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getMatchById(@PathVariable String id) throws ValidationException,
      ServiceException {

    if (!ValidationHelper.isNumber(id)) {
      throw new ValidationException(ValidationExceptionMessageEnum.INVALID_NUMBER.getMessage());
    }

    final MatchDto matchDto = this.matchService.findById(ConverterHelper.convertStringToLong(id));

    if (matchDto == null) {
      return new ResponseEntity<Object>(new MatchDto(), HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Object>(matchDto, HttpStatus.OK);
  }

  /**
   * Insere uma partida no banco.
   *
   * @param matchInputDto DTO com as informações da partida.
   * @param result Bind das validações.
   * @return ResponseEntity com a resposta HTTP adequada.
   * @throws ParseException Excecão que pode ser lançada ao converter a data.
   * @throws ServiceException Exceção caso não sejam encontrados os times.
   */
  @PostMapping
  public ResponseEntity<?> insertMatch(@RequestBody MatchInputDto matchInputDto,
      BindingResult result) throws ParseException, ServiceException {

    final MatchInputDtoValidator validator = new MatchInputDtoValidator();
    validator.validate(matchInputDto, result);

    if (result.hasErrors()) {
      return new ResponseEntity<>("Dados incorretos", HttpStatus.BAD_REQUEST);
    } else {
      this.matchService.insertMatch(matchInputDto);
    }
    return new ResponseEntity<Object>("Partida inserida", HttpStatus.OK);
  }

  /**
   * Insere um gol em uma partida.
   *
   * @param goalInputDto Dto com as informações do gol.
   * @return ResponseEntity com o status da resposta.
   * @throws ServiceException Exceção das classes de serviço.
   */
  @PutMapping(value = "/{matchId}/goals", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity insertGoal(@PathVariable Long matchId,
      @RequestBody GoalInputDto goalInputDto, BindingResult result) throws ServiceException {

    if (result.hasErrors()) {
      return new ResponseEntity<>("Dados inválidos", HttpStatus.BAD_REQUEST);
    }

    final GoalDto goalDto = this.matchService.insertGoalInMatch(goalInputDto);
    return new ResponseEntity<Object>(goalDto, HttpStatus.OK);
  }

  /**
   * Insere um cartão em uma partida.
   *
   * @param cardInputDto Dto com as informações do cartão.
   * @return ResponseEntity com o status da resposta.
   * @throws ServiceException Exceção das classes de serviço.
   */
  @PutMapping(value = "/{matchId}/cards", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity insertCard(@PathVariable Long matchId,
      @RequestBody CardInputDto cardInputDto, BindingResult result) throws ServiceException {

    if (result.hasErrors()) {
      return new ResponseEntity<>("Dados inválidos", HttpStatus.BAD_REQUEST);
    }

    this.matchService.insertCardInMatch(cardInputDto);
    return new ResponseEntity<Object>("Cartão inserido com sucesso.", HttpStatus.OK);
  }

  /**
   * Insere uma substituição em uma partida.
   *
   * @param matchId O identificador da partida onde o gol será inserido.
   * @param substitutionInputDto DTO com as informações a respeito da substituição.
   * @param result Objeto que valida o DTO.
   * @return ResponseEntity com o status da resposta.
   * @throws ServiceException Exceção das classes de serviço.
   */
  @PutMapping(value = "/{matchId}/substitutions", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity insertSubstitution(@PathVariable Long matchId,
      @RequestBody SubstitutionInputDto substitutionInputDto, BindingResult result)
      throws ServiceException {

    if (result.hasErrors()) {
      return new ResponseEntity<>("Dados inválidos", HttpStatus.BAD_REQUEST);
    }

    this.matchService.insertSubstitutionInMatch(substitutionInputDto);

    return new ResponseEntity<Object>("Substituição inserida com sucesso.", HttpStatus.OK);
  }


  /**
   * Obtém a lista dos estádios onde os jogos podem ser realizados.
   *
   * @return Lista com as informações do estádios em StadiumEnum.
   */
  @GetMapping(value = "/stadiums", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getStadiums() {
    final List<HashMap<String, String>> stadiumEnumList = StadiumEnum.getStadiumEnumList();
    return new ResponseEntity<Object>(stadiumEnumList, HttpStatus.OK);
  }

  @GetMapping(value = "/round/{roundNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getMatchesInRound(@PathVariable Long roundNumber)
      throws ValidationException {

    if (!ValidationHelper.isRoundNumber(roundNumber)) {
      throw new ValidationException(
          ValidationExceptionMessageEnum.INVALID_ROUND_NUMBER.name());
    }
    final List<MatchMinDto> matchMinDtos = this.matchService.getMatchesInRound(roundNumber);
    return new ResponseEntity<Object>(matchMinDtos, HttpStatus.OK);
  }

  /**
   * Obtém uma lista com os tipos de gols.
   *
   * @return Lista com os tipos de gols.
   */
  @GetMapping(value = "/goalType", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getGoalType() {
    final List<HashMap<String, String>> goalsTypeList = GoalTypeEnum.getGoalTypeEnumList();
    return new ResponseEntity<Object>(goalsTypeList, HttpStatus.OK);
  }
}
