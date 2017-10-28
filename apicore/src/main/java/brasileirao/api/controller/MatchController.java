package brasileirao.api.controller;

import java.text.ParseException;
import java.util.ArrayList;
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
  public ResponseEntity<?> getMatchById(@PathVariable Long id) {

    final Match match = this.matchService.findById(id);

    if (match != null) {
      final MatchDto matchDto = this.matchService.convertMatchToDto(match);
      return new ResponseEntity<>(matchDto, HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Não encontrado", HttpStatus.NOT_FOUND);
    }
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

      // this.insertMatches();
      this.matchService.insertMatch(matchInputDto);
    }
    return new ResponseEntity<Object>("Partida inserida", HttpStatus.OK);
  }

  // TODO ESTE MÉTODO DEVE SER REMOVIDO. USADO SOMENTE COMO FACILITADOR PARA INSERÇÃO DE PARTIDAS.
  @GetMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> insertMatches() throws ParseException, ServiceException {

    final HashMap<String, Long> clubs = new HashMap<>();
    clubs.put("ACG", 41L);
    clubs.put("CAM", 42L);
    clubs.put("CAP", 43L);
    clubs.put("AVA", 44L);
    clubs.put("BAH", 45L);
    clubs.put("BOT", 46L);
    clubs.put("CHA", 47L);
    clubs.put("COR", 48L);
    clubs.put("CFC", 49L);
    clubs.put("CRU", 50L);
    clubs.put("FLA", 51L);
    clubs.put("FLU", 52L);
    clubs.put("GRE", 53L);
    clubs.put("PAL", 54L);
    clubs.put("PON", 55L);
    clubs.put("SAN", 56L);
    clubs.put("SAO", 57L);
    clubs.put("SPO", 58L);
    clubs.put("VAS", 59L);
    clubs.put("VIT", 60L);

    final List<MatchInputDto> dtos = new ArrayList<>();

    final Long round = 28L;
    dtos.add(new MatchInputDto(round, "07/10/2017 16:00", StadiumEnum.MINEIRAO, clubs.get("CRU"),
        clubs.get("PON")));

    dtos.add(new MatchInputDto(round, "14/10/2017 19:00", StadiumEnum.MARACANA, clubs.get("VAS"),
        clubs.get("BOT")));

    dtos.add(new MatchInputDto(round, "14/10/2017 21:00", StadiumEnum.PACAEMBU, clubs.get("SAO"),
        clubs.get("CAP")));

    dtos.add(new MatchInputDto(round, "15/10/2017 17:00", StadiumEnum.MARACANA, clubs.get("FLU"),
        clubs.get("AVA")));

    dtos.add(new MatchInputDto(round, "15/10/2017 17:00", StadiumEnum.ILHA_DO_RETIRO, clubs
        .get("SPO"), clubs.get("CAM")));

    dtos.add(new MatchInputDto(round, "15/10/2017 17:00", StadiumEnum.ESTADIO_OLIMPICO, clubs
        .get("ACG"), clubs.get("PAL")));

    dtos.add(new MatchInputDto(round, "15/10/2017 17:00", StadiumEnum.ARENA_CONDA,
        clubs.get("CHA"), clubs.get("FLA")));

    dtos.add(new MatchInputDto(round, "15/10/2017 19:00", StadiumEnum.COUTO_PEREIRA, clubs
        .get("CFC"), clubs.get("GRE")));

    dtos.add(new MatchInputDto(round, "15/10/2017 19:00", StadiumEnum.FONTE_NOVA, clubs.get("BAH"),
        clubs.get("COR")));

    dtos.add(new MatchInputDto(round, "16/10/2017 20:00", StadiumEnum.PACAEMBU, clubs.get("SAN"),
        clubs.get("VIT")));

    for (MatchInputDto dto : dtos) {
      this.matchService.insertMatch(dto);
    }

    return new ResponseEntity<Object>("Inserido", HttpStatus.OK);

  }

  /**
   * Insere um gol e uma partida.
   *
   * @param goalInputDto Dto com as informações do gol.
   * @return ResponseEntity com o status da resposta.
   * @throws ServiceException Exceção das classes de serviço.
   */
  @PutMapping(value = "/goals", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity insertGoal(@RequestBody GoalInputDto goalInputDto, BindingResult result)
      throws ServiceException {

    if (result.hasErrors()) {
      return new ResponseEntity<>("Dados inválidos", HttpStatus.BAD_REQUEST);
    }

    final GoalDto goalDto = this.matchService.insertGoalInMatch(goalInputDto);
    return new ResponseEntity<Object>(goalDto, HttpStatus.OK);
  }

  /**
   * Insere um cartão.
   *
   * @param cardInputDto Dto com as informações do cartão.
   * @return ResponseEntity com o status da resposta.
   * @throws ServiceException Exceção das classes de serviço.
   */
  @PutMapping(value = "/cards", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity insertCard(@RequestBody CardInputDto cardInputDto, BindingResult result)
      throws ServiceException {

    if (result.hasErrors()) {
      return new ResponseEntity<>("Dados inválidos", HttpStatus.BAD_REQUEST);
    }

    this.matchService.insertCardInMatch(cardInputDto);
    return new ResponseEntity<Object>("Teste", HttpStatus.OK);
  }

  @PutMapping(value = "/{matchId}/substitutions", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity insertSubstitution(@PathVariable Long matchId,
      @RequestBody SubstitutionInputDto substitutionInputDto, BindingResult result)
      throws ServiceException {

    if (result.hasErrors()) {
      return new ResponseEntity<>("Dados inválidos", HttpStatus.BAD_REQUEST);
    }

    substitutionInputDto.setMatchId(matchId);
    this.matchService.insertSubstitutionInMatch(substitutionInputDto);

    return new ResponseEntity<Object>("Teste", HttpStatus.OK);
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
          ValidationExceptionMessageEnum.INVALID_ROUND_NUMBER.getMessage());
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
