package brasileirao.web.controller;

import brasileirao.api.persistence.Match;
import brasileirao.api.persistence.Player;
import brasileirao.api.service.ClubService;
import brasileirao.api.service.MatchService;
import brasileirao.api.service.PlayerService;
import brasileirao.web.enums.ClubTypeEnum;
import brasileirao.web.helper.ConverterHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


/**
 * Lida com as requisicões da aplicação Web que são referentes à entidade Match.
 */
@Controller
@RequestMapping(value = "admin/match")
public class AdminMatchController {


   /**
    * Classse de serviços das entidade Match.
    */
   @Autowired
   private MatchService matchService;

   /**
    * Classe de serviçoes da entidade Player.
    */
   @Autowired
   private PlayerService playerService;

   /**
    * Instância da classe de serviços da entidade <i>Club</i>
    */
   @Autowired
   private ClubService clubService;
   
   /**
    * Retorna a view de edicao de partida com o objeto de contexto.
    *
    * @param matchId Identificador da partida a ser editada.
    * @return Model and view com a view a ser recarregada.
    */
   @GetMapping(value = "/{matchId}")
   public ModelAndView editMatch(@PathVariable Long matchId, Model model) {
      final ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName("edit_match");

      final Match match = this.matchService.findById(matchId);
      model.addAttribute("match", this.matchService.convertMatchToDto(match));

      return modelAndView;
   }

   @PostMapping(value = "/persisteplayers")
   public ResponseEntity updateStartingPlayers(@RequestParam(value = "matchId") Long matchId,
                                               @RequestParam(value = "clubType") String clubType,
                                               @RequestParam(value = "isStartingPlayers") Boolean isStartingPlayers,
                                               @RequestParam(value = "playersIdList") String playersIdList) {

      final Match match = this.matchService.findById(matchId);
      final List<Player> playerList = new ArrayList<>();

      if (match != null) {

         final List<Long> idsList = ConverterHelper.convertStringWithIdsToList(playersIdList);

         for (Long playerId : idsList) {
            final Player player = this.playerService.findById(playerId);
            playerList.add(player);
         }


         if (clubType.equals(ClubTypeEnum.HOME_CLUB.getClubType())) {

            if (isStartingPlayers) {
               match.setHomeClubStartingPlayers(playerList);
            } else {
               match.setHomeClubSubstitutePlayers(playerList);
            }

         } else if (clubType.equals(ClubTypeEnum.VISITOR_CLUB.getClubType())) {

            if (isStartingPlayers) {
               match.setVisitorClubStartingPlayers(playerList);
            } else {
               match.setVisitorClubSubstitutePlayers(playerList);
            }
         }

         this.matchService.save(match);

      }

      return new ResponseEntity<Object>("Jogadores salvos", HttpStatus.OK);
   }
}
