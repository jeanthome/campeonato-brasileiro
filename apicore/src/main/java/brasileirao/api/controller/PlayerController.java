package brasileirao.api.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import brasileirao.api.dto.PlayerDto;
import brasileirao.api.dto.PlayerRegisterDto;
import brasileirao.api.enums.ValidationExceptionMessageEnum;
import brasileirao.api.exception.ServiceException;
import brasileirao.api.exception.ValidationException;
import brasileirao.api.helper.ConverterHelper;
import brasileirao.api.helper.ValidationHelper;
import brasileirao.api.service.PlayerService;

/**
 * Lida com requisições referentes à entidade <i>Player</i>
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/players")
public class PlayerController {

  /**
   * Instância da classe de serviços da entidade <i>Player</i>.
   */
  @Autowired
  private PlayerService playerService;

  /**
   * Retorna JSON com todos os jogadores cadastrados no banco.
   *
   * @return ResponseEntity Objeto com detalhes da requisição HTTP, como o Status.
   */
  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getAllPlayers() throws IOException, ValidationException,
      ServiceException {

    final List<PlayerDto> playerDtoList = this.playerService.getAllPlayers();
    return new ResponseEntity<>(playerDtoList, HttpStatus.FOUND);
  }

  /**
   * Retorna JSON que representa o jogador com o <b>id</b> especificado.
   *
   * @param playerId Identificador do jogador a ser buscado.
   * @return ResponseEntity Objeto com detalhes da requisição HTTP, como o Status.
   */
  @GetMapping(value = "/{playerId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getPlayerById(@PathVariable String playerId) throws IOException,
      ValidationException, ServiceException {

    if (!ValidationHelper.isNumber(playerId)) {
      throw new ValidationException(ValidationExceptionMessageEnum.INVALID_PLAYER_ID.name());
    }

    final PlayerDto playerDto =
        this.playerService.getPlayerById(ConverterHelper.convertStringToLong(playerId));

    if (playerDto != null) {
      return new ResponseEntity<>(playerDto, HttpStatus.FOUND);
    }

    return new ResponseEntity<>(new PlayerDto(), HttpStatus.NOT_FOUND);
  }

  /**
   * Obtém a imagem de um jogador específico.
   *
   * @param playerId Identificador do jogador do qual deseja-se obter a imagem.
   * @return {@link ResponseEntity} com a stream que contém a imagem do jogador.
   * @throws ValidationException Lançada caso o id do jogador seja inválido.
   * @throws IOException  Pode ser lançada no momento de adicioanr os links no DTO.
   * @throws ServiceException Lançada caso o jogador não seja encontrado.
   */
  @GetMapping(value = "/{playerId}/image", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getPlayerImage(@PathVariable String playerId)
      throws ValidationException, IOException, ServiceException {

    if (!ValidationHelper.isNumber(playerId)) {
      throw new ValidationException(ValidationExceptionMessageEnum.INVALID_PLAYER_ID.name());
    }
    return this.playerService.getPlayerImage(ConverterHelper.convertStringToLong(playerId));
  }

  @PostMapping
  public ResponseEntity<?> insertPlayer(@RequestBody PlayerRegisterDto playerRegisterDto,
      BindingResult result) {
    if (result.hasErrors()) {
      return new ResponseEntity<>("Dados inválidos", HttpStatus.BAD_REQUEST);
    } else {
      this.playerService.save(this.playerService
          .convertPlayerRegisterDtoToPlayer(playerRegisterDto));
    }

    return new ResponseEntity<Object>("Jogador cadastrado.", HttpStatus.OK);
  }

}
