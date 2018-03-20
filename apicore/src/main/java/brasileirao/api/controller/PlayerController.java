package brasileirao.api.controller;

import brasileirao.api.dto.PlayerDto;
import brasileirao.api.dto.PlayerRegisterDto;
import brasileirao.api.exception.ValidationException;
import brasileirao.api.persistence.Player;
import brasileirao.api.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
  public ResponseEntity<?> getAllPlayers() throws IOException, ValidationException {

    final Iterable<Player> playerIterable = this.playerService.findAll();
    final Iterator<Player> playerIterator = playerIterable.iterator();

    final List<PlayerDto> playerDtoList = new ArrayList<>();

    while (playerIterator.hasNext()) {
      final Player player = playerIterator.next();
      final PlayerDto playerDto = this.playerService.convertPlayerToDto(player);

      /* Adiciona link para o clube caso o jogador pertença a um. */
      if (player.getActualClub() != null) {
        playerDto.addLinksToPlayer(player.getId(), player.getActualClub().getId());
      } else {
        playerDto.addLinks(player.getId());
      }

      playerDtoList.add(playerDto);
    }

    if (playerIterable.iterator().hasNext()) {
      return new ResponseEntity<>(playerDtoList, HttpStatus.FOUND);
    } else {
      return new ResponseEntity<>("Não encontrado", HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Retorna JSON que representa o jogador com o <b>id</b> especificado.
   *
   * @param id Identificador do jogador a ser buscado.
   * @return ResponseEntity Objeto com detalhes da requisição HTTP, como o Status.
   */
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getPlayerById(@PathVariable Long id) throws IOException,
      ValidationException {

    final Player player = this.playerService.findById(id);

    if (player != null) {

      final PlayerDto playerDto = this.playerService.convertPlayerToDto(player);

      /* Adiciona link para o clube caso o jogador pertença a um. */
      if (player.getActualClub() != null) {
        playerDto.addLinksToPlayer(player.getId(), player.getActualClub().getId());
      } else {
        playerDto.addLinks(player.getId());
      }

      return new ResponseEntity<>(playerDto, HttpStatus.FOUND);
    } else {
      return new ResponseEntity<>("Não encontrado", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(value = "/{id}/image", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getPlayerImage(@PathVariable Long id) {
    final Player player = this.playerService.findById(id);

    if (player != null) {

      final ClassPathResource image =
          new ClassPathResource("/images/clubs/" + player.getActualClub().getFolderName() + "/"
              + player.getPhoto() + ".png");
      try {
        final InputStreamResource inputStreamResource =
            new InputStreamResource(image.getInputStream());
        return ResponseEntity.ok().contentLength(image.contentLength())
            .contentType(MediaType.IMAGE_PNG).body(inputStreamResource);

      } catch (IOException e) {
        return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
      }
    } else {
      return new ResponseEntity<>("Jogador não encontrado.", HttpStatus.NOT_FOUND);
    }
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
