package brasileirao.api.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import brasileirao.api.dto.MatchGoalsDto;
import brasileirao.api.dto.SubstitutionDto;
import brasileirao.api.enums.ClubTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import brasileirao.api.dto.CardDto;
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
  @GetMapping("/{matchId}")
  public ResponseEntity<?> getMatchById(@PathVariable String matchId) throws ValidationException,
      ServiceException {

    if (!ValidationHelper.isNumber(matchId)) {
      throw new ValidationException(ValidationExceptionMessageEnum.INVALID_NUMBER.name());
    }

    final MatchDto matchDto = this.matchService.findById(ConverterHelper.convertStringToLong(matchId));

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
   * @param matchId O id da partida onde o cartão será inserido.
   * @param goalInputDto Dto com as informações do gol.
   * @param result Objeto com possíveis erros de validação.
   * @return ResponseEntity com o status da requisição e os dados do gol inserido.
   * @throws ValidationException Em casos de erros de validação ou id da partida for inválido.
   * @throws ServiceException aso não seja encontrada a partida onde o cartão seria inserido.
   */
  @PutMapping(value = "/{matchId}/goals", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity insertGoal(@PathVariable String matchId,
      @RequestBody GoalInputDto goalInputDto, BindingResult result) throws ValidationException,
      ServiceException {

    if (result.hasErrors()) {
      throw new ValidationException(HttpStatus.BAD_REQUEST.name());
    }

    if (!ValidationHelper.isNumber(matchId)) {
      throw new ValidationException(ValidationExceptionMessageEnum.INVALID_MATCH_ID.name());
    }

    final GoalDto goalDto =
        this.matchService.insertGoalInMatch(ConverterHelper.convertStringToLong(matchId),
            goalInputDto);

    return new ResponseEntity<Object>(goalDto, HttpStatus.OK);
  }

  /**
   * Insere um cartão em uma partida.
   * 
   * @param matchId O id da partida onde o cartão será inserido.
   * @param cardInputDto Dto com as informações do cartão.
   * @param result Objeto com possíveis erros de validação.
   * @return ResponseEntity com o status da requisição e os dados do cartão inserido.
   * @throws ValidationException Em casos de erros de validação ou id da partida for inválido.
   * @throws ServiceException Caso não seja encontrada a partida onde o cartão seria inserido.
   */
  @PutMapping(value = "/{matchId}/cards", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity insertCard(@PathVariable String matchId,
      @RequestBody CardInputDto cardInputDto, BindingResult result) throws ValidationException,
      ServiceException {

    if (result.hasErrors()) {
      throw new ValidationException(HttpStatus.BAD_REQUEST.name());
    }

    if (!ValidationHelper.isNumber(matchId)) {
      throw new ValidationException(ValidationExceptionMessageEnum.INVALID_MATCH_ID.name());
    }

    final CardDto cardDto =
        this.matchService.insertCardInMatch(ConverterHelper.convertStringToLong(matchId),
            cardInputDto);
    return new ResponseEntity<Object>(cardDto, HttpStatus.OK);
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
  public ResponseEntity insertSubstitution(@PathVariable String matchId,
      @RequestBody SubstitutionInputDto substitutionInputDto, BindingResult result)
      throws ValidationException, ServiceException {

    if (result.hasErrors()) {
      throw new ValidationException(HttpStatus.BAD_REQUEST.name());
    }

    if (!ValidationHelper.isNumber(matchId)) {
      throw new ValidationException(ValidationExceptionMessageEnum.INVALID_MATCH_ID.name());
    }

    final SubstitutionDto substitutionDto =
        this.matchService.insertSubstitutionInMatch(ConverterHelper.convertStringToLong(matchId),
            substitutionInputDto);

    return new ResponseEntity<Object>(substitutionDto, HttpStatus.OK);
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

  /**
   * Obtém lista de partidas em uma rodada específica.
   * 
   * @param roundNumber A rodada a ser usada como filtro.
   * @return ResponseEntity com o status da resposta e os dados das partidas encontradas.
   * @throws ValidationException Caso a rodada passada seja inválida/
   */
  @GetMapping(value = "/round/{roundNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getMatchesInRound(@PathVariable String roundNumber)
      throws ValidationException {

    if (!ValidationHelper.isRoundNumber(roundNumber)) {
      throw new ValidationException(ValidationExceptionMessageEnum.INVALID_ROUND_NUMBER.name());
    }

    final List<MatchMinDto> matchMinDtos =
        this.matchService.getMatchesInRound(ConverterHelper.convertStringToLong(roundNumber));
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

  /**
   * Obtém a lista de gols de uma partida.
   *
   * @param matchId O identificador da partida cujos gols são requisitados.
   * @param clubType Atributo opcional, que permite filtrar os gols pelo time.
   * @return {@link ResponseEntity} com os gols encontrados e o status da requisição.
   * @throws ValidationException Caso algum dos atributos da requisição seja inválido.
   * @throws ServiceException Caso não exista uma partida com o id especificado.
   */
  @GetMapping(value = "/{matchId}/goals", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getMatchGoals(@PathVariable String matchId, @RequestParam(
      value = "clubType", required = false) String clubType) throws ValidationException,
      ServiceException {

    if (!ValidationHelper.isNumber(matchId)) {
      throw new ValidationException(ValidationExceptionMessageEnum.INVALID_MATCH_ID.name());
    }

    MatchGoalsDto matchGoalsDto = null;
    if (clubType == null) {
      matchGoalsDto = this.matchService.getMatchGoals(ConverterHelper.convertStringToLong(matchId));
    } else {
      final ClubTypeEnum clubTypeEnum = ClubTypeEnum.getByName(clubType);

      if (clubTypeEnum == null) {
        throw new ServiceException(ValidationExceptionMessageEnum.INVALID_CLUB_TYPE.name());
      }
      matchGoalsDto =
          this.matchService.getMatchGoals(ConverterHelper.convertStringToLong(matchId),
              clubTypeEnum);
    }

    return new ResponseEntity<Object>(matchGoalsDto, HttpStatus.OK);
  }
}
