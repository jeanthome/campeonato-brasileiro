package brasileirao.api.controller;

import brasileirao.api.dto.CoachDto;
import brasileirao.api.dto.PlayerDto;
import brasileirao.api.enums.ValidationExceptionMessageEnum;
import brasileirao.api.exception.ServiceException;
import brasileirao.api.exception.ValidationException;
import brasileirao.api.helper.ConverterHelper;
import brasileirao.api.helper.ValidationHelper;
import brasileirao.api.persistence.Coach;
import brasileirao.api.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Lida com as requisições referentes à entidade <i>Coach</i>
 */
@Controller
@RequestMapping("/coaches")
public class CoachController {

  /**
   * Instância da classe de serviços da entidade <i>Coach</i>.
   */
  @Autowired
  private CoachService coachService;

  /**
   * Retorna JSON com todos os técnicos cadastrados no banco.
   *
   * @return ResponseEntity Objeto com detalhes da requisição HTTP, como o Status.
   * @throws IOException Exceçao
   */
  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getAllCoaches() throws IOException, ValidationException,
      ServiceException {

    final List<CoachDto> coachDtoList = this.coachService.getAllCoaches();
    return new ResponseEntity<>(coachDtoList, HttpStatus.FOUND);
  }

  /**
   * Retorna JSON que representa o técnico com o <b>id</b> especificado.
   *
   * @param coachId Identificador do técnico a ser buscado.
   * @return ResponseEntity Objeto com detalhes da requisição HTTP, como o Status.
   */
  @GetMapping(value = "/{coachId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getCoachById(@PathVariable String coachId) throws IOException,
      ValidationException, ServiceException {

    if (!ValidationHelper.isNumber(coachId)) {
      throw new ValidationException(ValidationExceptionMessageEnum.INVALID_COACH_ID.name());
    }

    final CoachDto coachDto =
        this.coachService.getCoachById(ConverterHelper.convertStringToLong(coachId));

    if (coachDto != null) {
      return new ResponseEntity<>(coachDto, HttpStatus.FOUND);
    }

    return new ResponseEntity<>(new CoachDto(), HttpStatus.NOT_FOUND);
  }

  /**
   * Retorna JSON que representa o técnico com o <b>id</b> especificado.
   *
   * @param coachId Identificador do técnico a ser buscado.
   * @return ResponseEntity Objeto com detalhes da requisição HTTP, como o Status.
   * @throws IOException Lançada caso o id do jogador seja inválido.
   * @throws ValidationException  Pode ser lançada no momento de adicioanr os links no DTO.
   * @throws ServiceException Lançada caso o jogador não seja encontrado.
   */
  @GetMapping(value = "/{coachId}/image", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getCoachImage(@PathVariable String coachId) throws IOException,
      ValidationException, ServiceException {

    if (!ValidationHelper.isNumber(coachId)) {
      throw new ValidationException(ValidationExceptionMessageEnum.INVALID_COACH_ID.name());
    }
    return this.coachService.getCoachImage(ConverterHelper.convertStringToLong(coachId));
  }

  /**
   * Persiste uma instância da classe <i>Coach</i> no banco. A instância recebida deve possuir o
   * atributo <b>actualClub</b> nulo. Tal atributo pode ser atributo por meio do método
   * {@link brasileirao.api.controller.ClubController#setClubToCoach(Coach, Long)} .
   *
   * @param coach Instância da classe <i>Coach</i>, que será persistida.
   * @return Instância persistida.
   */
  @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createCoach(@RequestBody Coach coach) {
    this.coachService.save(coach);
    return new ResponseEntity<>("Salvo", HttpStatus.CREATED);
  }
}
