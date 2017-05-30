package brasileirao.api.controller;

import brasileirao.api.converter.ConvertHelper;
import brasileirao.api.dto.ClubDto;
import brasileirao.api.dto.PlayerDto;
import brasileirao.api.persistence.Club;
import brasileirao.api.persistence.Player;
import brasileirao.api.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Lida com requisições referentes à entidade <i>Player</i>
 */
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
   public ResponseEntity<?> getAllPlayers() throws IOException {

      final Iterable<Player> playerIterable = this.playerService.findAll();
      final Iterator<Player> playerIterator = playerIterable.iterator();

      final List<PlayerDto> playerDtoList = new ArrayList<>();

      while (playerIterator.hasNext()) {
         final Player player = playerIterator.next();
         PlayerDto playerDto = ConvertHelper.convertPlayerToDto(player);

         /*Adiciona link para o clube caso o jogador pertença a um.*/
         if (player.getActualClub() != null) {
            playerDto = addLinksToPlayer(playerDto, player.getId(), player.getActualClub().getId());
         } else {
            playerDto = addLinksToPlayer(playerDto, player.getId());
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
   public ResponseEntity<?> getPlayerById(@PathVariable Long id) throws IOException {

      final Player player = this.playerService.findById(id);

      if (player != null) {
         PlayerDto playerDto = ConvertHelper.convertPlayerToDto(player);
         playerDto = addLinksToPlayer(playerDto, player.getId(), player.getActualClub().getId());

         return new ResponseEntity<>(playerDto, HttpStatus.FOUND);
      } else {
         return new ResponseEntity<>("Não encontrado", HttpStatus.NOT_FOUND);
      }
   }

   @GetMapping(value = "/{id}/imagem", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<?> getPlayerImage(@PathVariable Long id) {
      final Player player = this.playerService.findById(id);

      if (player != null) {

         final ClassPathResource image = new ClassPathResource("/images/clubs/"
                 + player.getActualClub().getFolderName() + "/" + player.getPhoto() + ".png");
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

   /**
    * Adiciona links (self e escudo) no JSON de {@link Club}
    *
    * @param playerDto Instância que receberá os links
    * @param playerId  Identificador do jogador.
    * @return Instância de {@link ClubDto} com os links adicionados.
    * @throws IOException
    */
   private PlayerDto addLinksToPlayer(PlayerDto playerDto, Long playerId, Long clubId) throws IOException {
      playerDto.add(linkTo(methodOn(PlayerController.class).getPlayerById(playerId)).withSelfRel());
      playerDto.add(linkTo(methodOn(PlayerController.class).getPlayerImage(playerId)).withRel("image"));
      playerDto.add(linkTo(methodOn(ClubController.class).getClubById(clubId)).withRel("club"));
      return playerDto;
   }

   /**
    * Adiciona links (self e escudo) no JSON de {@link Club}
    *
    * @param playerDto Instância que receberá os links
    * @param playerId  Identificador do jogador.
    * @return Instância de {@link ClubDto} com os links adicionados.
    * @throws IOException
    */
   private PlayerDto addLinksToPlayer(PlayerDto playerDto, Long playerId) throws IOException {
      playerDto.add(linkTo(methodOn(PlayerController.class).getPlayerById(playerId)).withSelfRel());
      playerDto.add(linkTo(methodOn(PlayerController.class).getPlayerImage(playerId)).withRel("image"));
      return playerDto;
   }

//   @RequestMapping(value = "players/savePlayers", produces = MediaType.APPLICATION_JSON_VALUE,
//           method = RequestMethod.POST)
//   public Player saveListOfPlayers(@RequestBody List<Player> players) {
//
//      for (Player player : players) {
//         playerService.save(player);
//      }
//      return playerService.findByDisplayName("Guerrero");
//   }
//
//   @RequestMapping(value = "player/savePlayer", produces = MediaType.APPLICATION_JSON_VALUE,
//           method = RequestMethod.POST)
//   public Player saveListOfPlayers(@RequestBody Player player) {
//
//      this.playerService.save(player);
//
//      return playerService.findByDisplayName("Guerrero");
//   }

}
