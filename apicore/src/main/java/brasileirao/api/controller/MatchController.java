package brasileirao.api.controller;

import brasileirao.api.dto.MatchDto;
import brasileirao.api.persistence.Match;
import brasileirao.api.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Lida com as requisicões referentes à entidade {@link Match}
 */
@RestController
@RequestMapping(value = "/matches")
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
    * @return Instância de {@link MatchDto} caso a partida seja encontrada.
    * <i>null</i> caso contrário
    */
   @GetMapping(value = "/{id}")
   public ResponseEntity<?> getMatchById(@PathVariable Long id) {

      final Match match = this.matchService.findById(id);

      if (match != null) {
         final MatchDto matchDto = this.matchService.convertMatchToDto(match);
         return new ResponseEntity<>(matchDto, HttpStatus.OK);
      } else {
         return new ResponseEntity<>("Não encontrado", HttpStatus.NOT_FOUND);
      }

   }
}
