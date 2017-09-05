package brasileirao.api.controller;

import brasileirao.api.dto.MatchDto;
import brasileirao.api.dto.MatchInputDto;
import brasileirao.api.enums.StadiumEnum;
import brasileirao.api.exception.ServiceException;
import brasileirao.api.persistence.Match;
import brasileirao.api.service.MatchService;
import brasileirao.api.validator.MatchInputDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Lida com as requisicões referentes à entidade {@link Match}
 */
@CrossOrigin
@RestController
@RequestMapping("/matches")
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
   @GetMapping("/{id}")
   public ResponseEntity<?> getMatchById(@PathVariable Long id) {

      final Match match = this.matchService.findById(id);

      if (match != null) {
         final MatchDto matchDto = this.matchService.convertMatchToDto(match);
         return new ResponseEntity<>(matchDto, HttpStatus.OK);
      } else {
         return new ResponseEntity<>("Não encontrado", HttpStatus.NOT_FOUND);
      }
   }

   /**
    * Insere uma partida no banco.
    *
    * @param matchInputDto DTO com as informações da partida.
    * @param result        Bind das validações.
    * @return ResponseEntity com a resposta HTTP adequada.
    * @throws ParseException   Excecão que pode ser lançada ao converter a data.
    * @throws ServiceException Exceção caso não sejam encontrados os times.
    */
   @PostMapping
   public ResponseEntity<?> insertMatch(@RequestBody MatchInputDto matchInputDto,
                                        BindingResult result)
           throws ParseException, ServiceException {

      final MatchInputDtoValidator validator = new MatchInputDtoValidator();
      validator.validate(matchInputDto, result);

      if (result.hasErrors()) {
         return new ResponseEntity<>("Dados incorretos", HttpStatus.BAD_REQUEST);
      } else {
         this.matchService.insertMatch(matchInputDto);
      }
      return new ResponseEntity<Object>("Partida inserida", HttpStatus.OK);
   }

   /**
    * Obtém a lista dos estádios onde os jogos podem ser realizados.
    *
    * @return Lista com as informações do estádios em StadiumEnum.
    */
   @GetMapping(value = "/stadiums", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<?> getStadiums() {

      final List<HashMap<String, String>> stadiumEnumList = new ArrayList<>();

      for (StadiumEnum stadiumEnum : StadiumEnum.values()) {
         final HashMap<String, String> hashMap = new HashMap<>();
         hashMap.put("value", stadiumEnum.name());
         hashMap.put("label", stadiumEnum.getName());
         stadiumEnumList.add(hashMap);
      }
      return new ResponseEntity<Object>(stadiumEnumList, HttpStatus.OK);
   }
}
