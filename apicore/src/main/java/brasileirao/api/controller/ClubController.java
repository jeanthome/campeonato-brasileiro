package brasileirao.api.controller;

import brasileirao.api.persistence.Club;
import brasileirao.api.persistence.Coach;
import brasileirao.api.service.ClubService;
import brasileirao.api.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Lida com requisições referentes à entidade <i>Club</i>.
 */
@RestController
@RequestMapping(value = "/clubs")
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
    */
   @RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE,
           method = RequestMethod.GET)
   public ResponseEntity<?> getAllClubs() {

      final Iterable<Club> clubIterable = this.clubService.findAll();

      if (clubIterable.iterator().hasNext()) {
         return new ResponseEntity<>(clubIterable, HttpStatus.FOUND);
      } else {
         return new ResponseEntity<>("Não encontrado", HttpStatus.NOT_FOUND);
      }
   }

   /**
    * Retorna JSON que representa o clube com o 'id' especificado.
    *
    * @param id Identificador do clube a ser buscado.
    * @return ResponseEntity Objeto com detalhes da requisição HTTP, como o Status.
    */
   @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
           method = RequestMethod.GET)
   public ResponseEntity<?> getClubById(@PathVariable Long id) {

      final Club club = this.clubService.findById(id);
      if (club != null) {
         return new ResponseEntity<>(club, HttpStatus.FOUND);
      } else {
         return new ResponseEntity<>("Não encontrado", HttpStatus.NOT_FOUND);
      }
   }

   /**
    * Persiste lista de clubes no banco.
    *
    * @param clubs JSON com lista dos clubes a serem persistidos.
    * @return ResponseEntity Objeto com detalhes da requisição HTTP, como o Status.
    */
   @RequestMapping(value = "/clubs-list", produces = MediaType.APPLICATION_JSON_VALUE,
           method = RequestMethod.POST)
   public ResponseEntity<?> createClubs(@RequestBody List<Club> clubs) {

      for (Club club : clubs) {
         clubService.save(club);
      }
      return new ResponseEntity<>("Salvo", HttpStatus.CREATED);
   }


   /**
    * Atribui um clube à instância de <i>Coach</i> recebida e faz a persistência no banco. O atributo
    * <b>clubId</b> deve ser o identificador do clube a ser atribuído ao técnico. Retorna erro se o
    * atributo não corresponder a nenhuma instância da classe <i>Club</i>.
    *
    * @param coach  Instância de <i>Coach</i> a ser persistida.
    * @param clubId Identificador do clube a ser atribuido ao técnico,
    * @return ResponseEntity Objeto com detalhes da requisição HTTP, como o Status.
    * @see Club
    * @see Coach
    */
   @RequestMapping(value = "/{clubId}/coach", produces = MediaType.APPLICATION_JSON_VALUE,
           method = RequestMethod.POST)
   public ResponseEntity<?> setClubToCoach(@RequestBody Coach coach, @PathVariable Long clubId) {

      final Club club = this.clubService.findById(clubId);

      if (club != null) {
         coach.setActualClub(club);
         this.coachService.save(coach);
         return new ResponseEntity<>(coach, HttpStatus.CREATED);
      } else {
         return new ResponseEntity<>("Clube não encontrado", HttpStatus.NOT_FOUND);
      }
   }

   @RequestMapping(value = "/{clubId}/coach", produces = MediaType.APPLICATION_JSON_VALUE,
           method = RequestMethod.GET)
   public ResponseEntity<?> getCoachOfClub(@PathVariable Long clubId) {
      final Club club = this.clubService.findById(clubId);

      if (club != null) {
         final Coach coach = this.coachService.findByActualClub(club);

         if (coach != null) {
            return new ResponseEntity<>(coach, HttpStatus.FOUND);
         } else {
            return new ResponseEntity<>("Técnico não encontrado.", HttpStatus.NOT_FOUND);
         }
      } else {
         return new ResponseEntity<>("Clube não encontrado.", HttpStatus.NOT_FOUND);
      }
   }
}
