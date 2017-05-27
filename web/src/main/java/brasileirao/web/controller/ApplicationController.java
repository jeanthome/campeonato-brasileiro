package brasileirao.web.controller;

import brasileirao.api.persistence.Club;
import brasileirao.api.persistence.Player;
import brasileirao.api.service.ClubService;
import brasileirao.api.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Lida com requisições que não são referentes a nenhuma entidade.
 */
@Controller
public class ApplicationController {


   @Autowired
   private ClubService clubService;

   @Autowired
   private PlayerService playerService;


   /**
    * Retorna formulário de teste.
    *
    * @return ModelAndView com o formulário a ser exibido.
    * */
   @RequestMapping(value = "/form", method = RequestMethod.GET)
   public ModelAndView index() {
      final ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName("index");

      final Iterable<Club> clubs = this.clubService.findAll();

      modelAndView.addObject("player", new Player() );
      return modelAndView;
   }

   @RequestMapping(value = "/insert_player", method = RequestMethod.POST)
   public ModelAndView insertNewPlayer(@Valid @ModelAttribute("player")Player player, BindingResult result){

      if (result.hasErrors() ){
         ModelAndView modelAndView = new ModelAndView("redirect:/form");
         return modelAndView;
      } else {
         this.playerService.save(player);
      }

      ModelAndView modelAndView = new ModelAndView("redirect:/index");
      modelAndView.addObject("player", new Player() );
      return modelAndView;
      
   }
}
