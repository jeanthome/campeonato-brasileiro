package brasileirao.web.controller;

import brasileirao.api.persistence.Club;
import brasileirao.api.persistence.Match;
import brasileirao.api.persistence.Player;
import brasileirao.api.service.ClubService;
import brasileirao.api.service.MatchService;
import brasileirao.api.service.PlayerService;
import brasileirao.api.dto.PlayerRegisterDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Lida com requisições que não são referentes a nenhuma entidade.
 */
@Controller
public class ApplicationController {

   /**
    * Constante para a palavra "player".
    */
   public static final String PLAYER = "player";

   /**
    * Constante para a palavra "clubs"
    */
   public static final String CLUBS = "clubs";


   /**
    * Classe de serviços da entidade Club.
    */
   @Autowired
   private ClubService clubService;

   /**
    * Instância da classe de serviços da entidade <i>Player</i>
    */
   @Autowired
   private PlayerService playerService;

   /**
    * Instância da classe de serviços da entidade <i>Match</i>
    */
   @Autowired
   private MatchService matchService;
   
   /**
    * Retorna formulário de cadastro de jogador..
    *
    * @return ModelAndView com o formulário a ser exibido.
    */
   @RequestMapping(value = "/form", method = RequestMethod.GET)
   public ModelAndView form() {
      final ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName("formRegistryPlayer");

      final Iterable<Club> clubs = this.clubService.findAll();

      modelAndView.addObject(PLAYER, new PlayerRegisterDto());
      modelAndView.addObject(CLUBS, clubs);
      return modelAndView;
   }
   /**
    * Retorna formulário de cadastro de partidas.
    *
    * @return ModelAndView com o formulário a ser exibido.
    */
   @RequestMapping(value = "/match", method = RequestMethod.GET)
   public ModelAndView formMatch() {
      final ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName("cadastroPartida");

      final Iterable<Club> clubs = this.clubService.findAll();

      modelAndView.addObject("match", new Match());
      modelAndView.addObject(CLUBS, clubs);
      return modelAndView;
   }
}

