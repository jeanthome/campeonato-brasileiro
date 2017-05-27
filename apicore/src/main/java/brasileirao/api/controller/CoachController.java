package brasileirao.api.controller;

import brasileirao.api.persistence.Coach;
import brasileirao.api.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Lida com as requisições referentes à entidade <i>Coach</i>
 */
@Controller
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
    */
   @RequestMapping(value = "/coaches", produces = MediaType.APPLICATION_JSON_VALUE,
           method = RequestMethod.GET)
   public ResponseEntity<?> getAllCoaches() {

      final Iterable<Coach> coachIterable = this.coachService.findAll();

      if (coachIterable.iterator().hasNext()) {
         return new ResponseEntity<>(coachIterable, HttpStatus.FOUND);
      } else {
         return new ResponseEntity<>("Não encontrado", HttpStatus.NOT_FOUND);
      }
   }

   /**
    * Retorna JSON que representa o técnico com o <b>id</b> especificado.
    *
    * @param id Identificador do técnico a ser buscado.
    * @return ResponseEntity Objeto com detalhes da requisição HTTP, como o Status.
    */
   @RequestMapping(value = "/coaches/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
           method = RequestMethod.GET)
   public ResponseEntity<?> getCoachById(@PathVariable Long id) {

      final Coach coach = this.coachService.findById(id);

      if (coach != null) {
         return new ResponseEntity<>(coach, HttpStatus.FOUND);
      } else {
         return new ResponseEntity<>("Não encontrado", HttpStatus.NOT_FOUND);
      }
   }

   /**
    * Persiste uma instância da classe <i>Coach</i> no banco. A instância recebida deve possuir o
    * atributo <b>actualClub</b> nulo. Tal atributo pode ser atributo por meio do método
    * {@link brasileirao.api.controller.ClubController#setClubToCoach(Coach, Long)}}.
    *
    * @param coach Instância da classe <i>Coach</i>, que será persistida.
    * @return Instância persistida.
    */
   @RequestMapping(value = "/coaches", produces = MediaType.APPLICATION_JSON_VALUE,
           method = RequestMethod.POST)
   public ResponseEntity<?> createCoach(@RequestBody Coach coach) {

      this.coachService.save(coach);
      return new ResponseEntity<>("Salvo", HttpStatus.CREATED);
   }
}
