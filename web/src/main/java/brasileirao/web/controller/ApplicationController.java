package brasileirao.web.controller;

import brasileirao.api.persistence.Club;
import brasileirao.api.persistence.Match;
import brasileirao.api.persistence.Player;
import brasileirao.api.service.ClubService;
import brasileirao.api.service.MatchService;
import brasileirao.api.service.PlayerService;
import brasileirao.web.dto.PlayerRegisterDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    * Instância da classe de serviços da entidade <i>Match</i>
    */
   @Autowired
   private MatchService matchService;

   /**
    * Retorna a home page dos sistema.
    *
    * @return ModelAndView com a view a ser carregada.
    */
   @GetMapping(value = "/{roundNumber}")
   public ModelAndView index(@PathVariable Long roundNumber) {
      final ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName("home");

      final List<Match> matches = this.matchService.getMatchesInRound(roundNumber);
      modelAndView.addObject("matches", matches);
      return modelAndView;
   }

   /**
    * Retorna a view de edicao de partida com o objeto de contexto.
    *
    * @param matchId Identificador da partida a ser editada.
    * @return Model and view com a view a ser recarregada.
    */
   @GetMapping(value = "/edit/{matchId}")
   public ModelAndView editMatch(@PathVariable Long matchId, Model model) {
      final ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName("edit_match");

      final Match match = this.matchService.findById(matchId);

      model.addAttribute("club", this.clubService.convertClubToDto(match.getHomeClub()));

      model.addAttribute("match", this.matchService.convertMatchToDto(match));

      System.out.print(match.getHomeClub().getFullName());
      return modelAndView;
   }

   /**
    * Retorna formulário de teste.
    *
    * @return ModelAndView com o formulário a ser exibido.
    */
   @RequestMapping(value = "/form", method = RequestMethod.GET)
   public ModelAndView form() {
      final ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName("formRegistryPlayer");

      final Iterable<Club> clubs = this.clubService.findAll();

      modelAndView.addObject(PLAYER, new PlayerRegisterDto());
      modelAndView.addObject("clubs", clubs);
      return modelAndView;
   }

   /**
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
         this.playerService.save(this.convertPlayerRegisterDtoToPlayer(playerRegisterDto));
      }

      modelAndView.addObject(PLAYER, new Player());
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
      modelAndView.addObject("clubs", clubs);
      return modelAndView;
   }

   /**
    * Persiste uma partida no banco de dados.
    *
    * @param match  Entidada de {@link Match} a ser persistida.
    * @param result Resultado da validação dos campos obrigatórios.
    * @return View de cadsatro de partida.
    */
   @PostMapping(value = "insert_match")
   public ModelAndView insertNewMatch(@Valid Match match, BindingResult result) {

      final ModelAndView modelAndView = new ModelAndView("redirect:/match");
      if (result.hasErrors()) {
         return modelAndView;
      } else {
         this.matchService.save(match);
      }
      return modelAndView;
   }

   /**
    * Converte DTO da entidade Player em seu respectivo objeto.
    *
    * @param playerRegisterDto DTO com os dados.
    * @return Instancia de {@link Player}.
    */
   public Player convertPlayerRegisterDtoToPlayer(PlayerRegisterDto playerRegisterDto) {

      final ModelMapper modelMapper = new ModelMapper();
      final Player player = modelMapper.map(playerRegisterDto, Player.class);

      if (playerRegisterDto.getActualClubId() != null) {
         final Club club = this.clubService.findById(playerRegisterDto.getActualClubId());
         player.setActualClub(club);
      }

      if (playerRegisterDto.getNationality().length() < 3) {
         player.setNationality("Brasileiro");
      }
      return player;
   }

}

