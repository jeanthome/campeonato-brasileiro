package brasileirao.api.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import brasileirao.api.dto.ClubDto;
import brasileirao.api.dto.CoachDto;
import brasileirao.api.dto.PlayerDto;
import brasileirao.api.enums.ValidationExceptionMessageEnum;
import brasileirao.api.exception.ServiceException;
import brasileirao.api.exception.ValidationException;
import brasileirao.api.helper.ConverterHelper;
import brasileirao.api.helper.ValidationHelper;
import brasileirao.api.persistence.Club;
import brasileirao.api.persistence.Coach;
import brasileirao.api.service.ClubService;
import brasileirao.api.service.CoachService;

/**
 * Lida com requisições referentes à entidade <i>Club</i>.
 */
@CrossOrigin
@RestController
@RequestMapping("/clubs")
public class ClubController {

  /**
   * Instância da classe de serviços da entidade <i>Club</i>
   */
  @Autowired
  private ClubService clubService;

  /**
   * Instância da classe de serviços da entidade <i>Coach</i>
   */
  @Autowired
  private CoachService coachService;

  /**
   * Retorna JSON com todos os clubes cadastrados no banco.
   *
   * @return ResponseEntity Objeto com detalhes da requisição HTTP, como o Status.
   * @throws IOException Pode ser lançada no momento de adicioanr os links no DTO.
   * @throws ValidationException Pode ser lançada no momento de adicioanr os links no DTO.
   * @throws ServiceException Pode ser lançada no momento de adicioanr os links no DTO.
   */
  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getAllClubs() throws IOException, ValidationException, ServiceException {

    final List<ClubDto> clubDtoList = this.clubService.getAllClubs();
    return new ResponseEntity<>(clubDtoList, HttpStatus.OK);
  }

  /**
   * Retorna JSON que representa o clube com o 'id' especificado.
   *
   * @param clubId Identificador do clube a ser buscado.
   * @return ResponseEntity Objeto com detalhes da requisição HTTP, como o Status.
   * @throws IOException Pode ser lançada no momento de adicioanr os links no DTO.
   * @throws ValidationException Pode ser lançada no momento de adicioanr os links no DTO.
   * @throws ServiceException Pode ser lançada no momento de adicioanr os links no DTO.
   */
  @GetMapping(value = "/{clubId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getClubById(@PathVariable String clubId) throws IOException,
      ValidationException, ServiceException {

    if (!ValidationHelper.isNumber(clubId)) {
      throw new ValidationException(ValidationExceptionMessageEnum.INVALID_CLUB_ID.getMessage());
    }

    final ClubDto clubDto =
        this.clubService.getClubById(ConverterHelper.convertStringToLong(clubId));

    if (clubDto != null) {
      return new ResponseEntity<>(clubDto, HttpStatus.FOUND);
    } else {
      return new ResponseEntity<>("Não encontrado", HttpStatus.NOT_FOUND);
    }

  }

  /**
   * Atribui um clube à instância de <i>Coach</i> recebida e faz a persistência no banco. O atributo
   * <b>clubId</b> deve ser o identificador do clube a ser atribuído ao técnico. Retorna erro se o
   * atributo não corresponder a nenhuma instância da classe <i>Club</i>.
   *
   * @param coach Instância de <i>Coach</i> a ser persistida.
   * @param clubId Identificador do clube a ser atribuido ao técnico,
   * @return ResponseEntity Objeto com detalhes da requisição HTTP, como o Status.
   * @see Club
   * @see Coach
   */
  @PostMapping(value = "/{clubId}/coach", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> setClubToCoach(@RequestBody Coach coach, @PathVariable Long clubId) {

    final Club club = this.clubService.findById(clubId);


    if (club != null) {
      coach.setActualClub(club);
      this.coachService.save(coach);
      final CoachDto coachDto = this.coachService.convertCoachToDto(coach);
      return new ResponseEntity<>(coachDto, HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>("Clube não encontrado", HttpStatus.NOT_FOUND);
    }
  }


  /**
   * Obtém os dados do técnico de um clube específico.
   *
   * @param clubId Identificador do clube no qual o técnico trabalha.
   * @return Instância de {@link ResponseEntity} com os dados encontrados e o resultado da
   *         requisição.
   * @throws IOException Pode ser lançada ao adicionar os links no DTO.
   * @throws ValidationException Lançada caso o Id seja inválido.
   * @throws ServiceException Lançada caso o clube ou o técnico não sejam encontrados.
   */
  @GetMapping(value = "/{clubId}/coach", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getClubCoach(@PathVariable String clubId) throws IOException,
      ValidationException, ServiceException {

    if (!ValidationHelper.isNumber(clubId)) {
      throw new ValidationException(ValidationExceptionMessageEnum.INVALID_CLUB_ID.getMessage());
    }

    final CoachDto coachDto =
        this.clubService.getClubCoach(ConverterHelper.convertStringToLong(clubId));

    if (coachDto == null) {
      return new ResponseEntity<>("Técnico não encontrado.", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(coachDto, HttpStatus.FOUND);
  }


  /**
   * Obtém a lista de jogadores de um clube específico.
   *
   * @param clubId O identificador do clube do qual deseja-se obter a lista de jogadores.
   * @return Lista de {@link PlayerDto} com os dados dos jogadores encontrados.
   * @throws IOException Pode ser lançada no momento de adicioanr os links no DTO.
   * @throws ValidationException Lançada caso o id do clube seja inválido.
   * @throws ServiceException Lançada caso o clube não seja encontrado.
   */
  @GetMapping(value = "/{clubId}/players", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getPlayers(@PathVariable String clubId) throws IOException,
      ValidationException, ServiceException {

    if (!ValidationHelper.isNumber(clubId)) {
      throw new ValidationException(ValidationExceptionMessageEnum.INVALID_CLUB_ID.getMessage());
    }

    final List<PlayerDto> playerDtoList =
        this.clubService.getClubPlayers(ConverterHelper.convertStringToLong(clubId));

    return new ResponseEntity<Object>(playerDtoList, HttpStatus.OK);
  }

  /**
   * Obtém o escudo do clube.
   *
   * @param clubId O identificador do clube do qual deseja-se obter o escudo.
   * @return Stream com o escudo do clube.
   * @throws IOException
   */
  @GetMapping(value = "/{clubId}/badge", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getBadge(@PathVariable Long clubId) throws IOException {

    final Club club = this.clubService.findById(clubId);
    if (club != null) {

      // TODO Mover para a classe de servico.
      final ClassPathResource image =
          new ClassPathResource("/images/clubs/" + club.getFolderName() + "/" + club.getImage()
              + ".png");
      try {
        final InputStreamResource inputStreamResource =
            new InputStreamResource(image.getInputStream());
        return ResponseEntity.ok().contentLength(image.contentLength())
            .contentType(MediaType.IMAGE_PNG).body(inputStreamResource);

      } catch (IOException e) {
        return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
      }
    } else {
      return new ResponseEntity<>("Clube não encontrado", HttpStatus.NOT_FOUND);
    }
  }

}
