package brasileirao.web.controller;

import brasileirao.api.persistence.Club;
import brasileirao.api.persistence.Player;
import brasileirao.api.service.ClubService;
import brasileirao.api.service.PlayerService;
import brasileirao.web.dto.PlayerRegisterDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Lida com requisições que não são referentes a nenhuma entidade.
 */
@Controller
public class ApplicationController {

   /**Constante para a palavra "player".*/
   public static final String PLAYER = "player";

   /**
    * Instância da classe de serviços da entidade <i>Club</i>
    */
   @Autowired
   private ClubService clubService;

   /**
    * Instância da classe de serviços da entidade <i>Player</i>
    */
   @Autowired
   private PlayerService playerService;

   /**
    * Retorna formulário de teste.
    *
    * @return ModelAndView com o formulário a ser exibido.
    */
   @RequestMapping(value = "/form", method = RequestMethod.GET)
   public ModelAndView index() {
      final ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName("index");

      final Iterable<Club> clubs = this.clubService.findAll();

      modelAndView.addObject(PLAYER, new PlayerRegisterDto());
      modelAndView.addObject("clubs", clubs);
      return modelAndView;
   }

   /***
    * Insere um jogador no banco de dados.
    *
    * @param playerRegisterDto Instância do jogador a ser inserido.
    * @param result Instância que BindResult com informaçÕes dos possíveis erros de bind.
    * @return Instância de <i>ModelAndView</i> com a nova tela.
    */
   @RequestMapping(value = "/insert_player", method = RequestMethod.POST)
   public ModelAndView insertNewPlayer(@Valid PlayerRegisterDto playerRegisterDto, BindingResult result) {

      final ModelAndView modelAndView = new ModelAndView("redirect:/form");
      if (result.hasErrors()) {
         return modelAndView;
      } else {
         this.playerService.save( this.convertPlayerRegisterDtoDoPlayer(playerRegisterDto) );
      }

      modelAndView.addObject(PLAYER, new Player());
      return modelAndView;
   }


   public Player convertPlayerRegisterDtoDoPlayer(PlayerRegisterDto playerRegisterDto) {

      final ModelMapper modelMapper = new ModelMapper();
      final Player player = modelMapper.map(playerRegisterDto, Player.class);

      if (playerRegisterDto.getActualClubId() != null ){
         final Club club = this.clubService.findById(playerRegisterDto.getActualClubId());
         player.setActualClub(club);
      }

      if (playerRegisterDto.getNationality().length() < 3 ){
         player.setNationality("Brasileiro");
      }
      return player;
   }
}
