package brasileirao.api.controller;

import brasileirao.api.dto.ClubDto;
import brasileirao.api.dto.CoachDto;
import brasileirao.api.dto.PlayerDto;
import brasileirao.api.persistence.Club;
import brasileirao.api.persistence.Coach;
import brasileirao.api.persistence.Player;
import brasileirao.api.service.ClubService;
import brasileirao.api.service.CoachService;
import brasileirao.api.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Lida com requisições referentes à entidade <i>Club</i>.
 */
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

   /***
    * Instância da classe de serviços da entidade {@link brasileirao.api.persistence.Player}
    */
   @Autowired
   private PlayerService playerService;

   /**
    * Retorna JSON com todos os clubes cadastrados no banco.
    *
    * @return ResponseEntity Objeto com detalhes da requisição HTTP, como o Status.
    * @throws IOException Exceção a ser lançada
    */
   @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<?> getAllClubs() throws IOException {

      final Iterable<Club> clubIterable = this.clubService.findAll();
      final Iterator<Club> clubIterator = clubIterable.iterator();

      final List<ClubDto> clubDtoList = new ArrayList<>();
      while (clubIterator.hasNext()) {
         final Club club = clubIterator.next();
         final ClubDto clubDto = this.clubService.convertClubToClubDto(club);
         clubDto.addLinks(club.getId());
         clubDtoList.add(clubDto);
      }

      return new ResponseEntity<>(clubDtoList, HttpStatus.OK);

   }

   /**
    * Retorna JSON que representa o clube com o 'id' especificado.
    *
    * @param id Identificador do clube a ser buscado.
    * @return ResponseEntity Objeto com detalhes da requisição HTTP, como o Status.
    * @throws IOException Exceção para arquivo não encontrado.
    */
   @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<?> getClubById(@PathVariable Long id) throws IOException {

      final Club club = this.clubService.findById(id);
      if (club != null) {

         final ClubDto clubDto = this.clubService.convertClubToClubDto(club);
         clubDto.addLinks(club.getId());
         return new ResponseEntity<>(clubDto, HttpStatus.FOUND);

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
   @PostMapping(value = "/clubs-list", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<?> createClubs(@RequestBody List<Club> clubs) {

      for (Club club : clubs) {
         clubService.save(club);
      }
      return new ResponseEntity<>("Salvo", HttpStatus.CREATED);
   }

   /**
    * Atribui um clube à instância de <i>Coach</i> recebida e faz a persistência no banco.
    * O atributo <b>clubId</b> deve ser o identificador do clube a ser atribuído ao técnico.
    * Retorna erro se o atributo não corresponder a nenhuma instância da classe <i>Club</i>.
    *
    * @param coach  Instância de <i>Coach</i> a ser persistida.
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

   @GetMapping(value = "/{clubId}/coach", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<?> getCoachOfClub(@PathVariable Long clubId) throws IOException {
      final Club club = this.clubService.findById(clubId);

      if (club != null) {

         final Coach coach = this.coachService.findByActualClub(club);

         if (coach != null) {
            final CoachDto coachDto = this.coachService.convertCoachToDto(coach);
            coachDto.addLinks(club.getId());
            return new ResponseEntity<>(coachDto, HttpStatus.FOUND);

         } else {
            return new ResponseEntity<>("Técnico não encontrado.", HttpStatus.NOT_FOUND);
         }

      } else {
         return new ResponseEntity<>("Clube não encontrado.", HttpStatus.NOT_FOUND);
      }
   }

   @GetMapping(value = "/{clubId}/players", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<?> getPlayers(@PathVariable Long clubId) throws IOException {

      final Club club = this.clubService.findById(clubId);
      final List<PlayerDto> playerDtoList = new ArrayList<>();

      /*Ordena jogadores por posição*/
      Collections.sort(club.getPlayerList());

      for (Player player : club.getPlayerList()) {
         final PlayerDto playerDto = this.playerService.convertPlayerToDto(player);
         playerDto.addLinksToPlayer(player.getId(), clubId);
         playerDtoList.add(playerDto);
      }
      return new ResponseEntity<Object>(playerDtoList, HttpStatus.OK);
   }

   @GetMapping(value = "/{clubId}/badge", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<?> getBadge(@PathVariable Long clubId) throws IOException {

      final Club club = this.clubService.findById(clubId);
      if (club != null) {

         //TODO Mover para a classe de servico.
         final ClassPathResource image = new ClassPathResource("/images/clubs/"
                 + club.getFolderName() + "/" + club.getImage() + ".png");
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

