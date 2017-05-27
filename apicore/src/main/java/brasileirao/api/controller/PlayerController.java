package brasileirao.api.controller;

import brasileirao.api.persistence.Player;
import brasileirao.api.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
   @RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE,
           method = RequestMethod.GET)
   public ResponseEntity<?> getAllPlayers() {

      final Iterable<Player> playerIterable = this.playerService.findAll();

      if (playerIterable.iterator().hasNext()) {
         return new ResponseEntity<>(playerIterable, HttpStatus.FOUND);
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
   @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
           method = RequestMethod.GET)
   public ResponseEntity<?> getPlayerById(@PathVariable Long id) {

      final Player player = this.playerService.findById(id);

      if (player != null) {
         return new ResponseEntity<>(player, HttpStatus.FOUND);
      } else {
         return new ResponseEntity<>("Não encontrado", HttpStatus.NOT_FOUND);
      }
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
