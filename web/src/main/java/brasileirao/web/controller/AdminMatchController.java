package brasileirao.web.controller;

import brasileirao.api.dto.GoalInputDto;
import brasileirao.api.enums.ClubTypeEnum;
import brasileirao.api.exception.ServiceException;
import brasileirao.api.exception.ValidationException;
import brasileirao.api.helper.ValidationHelper;
import brasileirao.api.persistence.Match;
import brasileirao.api.persistence.PlayerInMatch;
import brasileirao.api.service.ClubService;
import brasileirao.api.service.MatchService;
import brasileirao.api.service.PlayerInMatchService;
import brasileirao.api.service.PlayerService;
import brasileirao.web.helper.ConverterHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


/**
 * Lida com as requisicões da aplicação Web que são referentes à entidade Match.
 */
@Controller
@RequestMapping("admin/match")
public class AdminMatchController {


   /**
    * Classse de serviços das entidade Match.
    */
   @Autowired
   private MatchService matchService;

   /**
    * Classe de serviços da entidade Player.
    */
   @Autowired
   private PlayerService playerService;

   /**
    * Instância da classe de serviços da entidade <i>Club</i>
    */
   @Autowired
   private ClubService clubService;

   /**
    * Classe de ser serviços da entidade PlayerInMatch
    */
   @Autowired
   private PlayerInMatchService playerInMatchService;

   /**
    * Retorna a view de edicao de partida com o objeto de contexto.
    *
    * @param matchId Identificador da partida a ser editada.
    * @param model   Model da página
    * @return Model and view com a view a ser recarregada.
    */
   @GetMapping("/{matchId}")
   public ModelAndView editMatch(@PathVariable Long matchId, Model model) {
      final ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName("edit_match");

      final Match match = this.matchService.findById(matchId);
      model.addAttribute("match", this.matchService.convertMatchToDto(match));

      return modelAndView;
   }

   /**
    * Persiste a escalação de jogadores em um partida.
    *
    * @param matchId           Identificador da partida sendo editada.
    * @param clubType          O tipo do clube sendo editado (mandante ou visitante)
    * @param isStartingPlayers Flag que indica se a lista recebida é de ids de jogadores escalados
    *                          como titulares ou não.
    * @param playersIdList     Lista com os ids dos jogadores, usando (;) como separador.
    * @return ResponseEntity com a defina resposta.
    * @throws ValidationException Exceção de validação.
    */
   @PostMapping("/persistePlayers")
   public ResponseEntity updateStartingPlayers(@RequestParam("matchId") Long matchId,
                                               @RequestParam("clubType") String clubType,
                                               @RequestParam("isStartingPlayers") Boolean
                                                       isStartingPlayers,
                                               @RequestParam("playersIdList") String
                                                       playersIdList) throws ValidationException {

      final Match match = this.matchService.findById(matchId);
      List<PlayerInMatch> playerInMatchList = new ArrayList<>();

      if (match != null) {

         List<Long> idsList = new ArrayList<>();

         if (!ValidationHelper.isEmptyOrVoid(playersIdList)) {
            idsList = ConverterHelper.convertStringWithIdsToList(playersIdList);
         }

         playerInMatchList = this.playerInMatchService.getPlayerListByIdList(idsList);

         if (clubType.equals(ClubTypeEnum.HOME_CLUB.getClubType())) {

            if (isStartingPlayers) {
               match.setHomeClubStartingPlayers(playerInMatchList);
            } else {
               match.setHomeClubSubstitutePlayers(playerInMatchList);
            }

         } else if (clubType.equals(ClubTypeEnum.VISITOR_CLUB.getClubType())) {

            if (isStartingPlayers) {
               match.setVisitorClubStartingPlayers(playerInMatchList);
            } else {
               match.setVisitorClubSubstitutePlayers(playerInMatchList);
            }
         }
         this.matchService.save(match);
      }
      return new ResponseEntity<Object>("Jogadores salvos", HttpStatus.OK);
   }

   /**
    * Insere um gol e uma partida.
    *
    * @param goalInputDto Dto com as informações do gol.
    * @return ResponseEntity com o status da resposta.
    * @throws ServiceException Exceção das classes de serviço.
    */
   @PutMapping(value = "/goal", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity insertGoal(@RequestBody GoalInputDto goalInputDto)
           throws ServiceException {

      this.matchService.insertGoalInMatch(goalInputDto);
      return new ResponseEntity<Object>("Gol inserido.", HttpStatus.OK);

   }
}
