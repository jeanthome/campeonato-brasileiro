package brasileirao.api.dto;

import brasileirao.api.controller.ClubController;
import brasileirao.api.controller.PlayerController;
import brasileirao.api.persistence.Club;
import org.springframework.hateoas.ResourceSupport;

import java.io.IOException;
import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class PlayerDto extends ResourceSupport {

   /**
    * Id do jogador.
    */
   private Long id;

   /**
    * Nome de exibição do jogador.
    */
   private String displayName;

   /***
    * Idade do jogador.
    */
   private Integer age;

   /**
    * Posição do jogador. (Nome e abreviação da posição)
    */
   private Map<String, String> position;

   /***
    * Número da camisa do Jogador.
    */
   private Long number;

   public void setId(Long id) {
      this.id = id;
   }

   public String getDisplayName() {
      return displayName;
   }

   public void setDisplayName(String displayName) {
      this.displayName = displayName;
   }

   public Map<String, String> getPosition() {
      return position;
   }

   public void setPosition(Map<String, String> position) {
      this.position = position;
   }

   public Long getNumber() {
      return number;
   }

   public void setNumber(Long number) {
      this.number = number;
   }

   public Integer getAge() {
      return age;
   }

   public void setAge(Integer age) {
      this.age = age;
   }

   /**
    * Adiciona links (self, image e acualClub) no JSON de {@link PlayerDto}
    *
    * @param playerId  Identificador do jogador.
    * @param clubId Identificador do club ao qual o jogador pertence.
    * @return Instância de {@link ClubDto} com os links adicionados.
    * @throws IOException Exceção lançada no método GetPlayerImage.
    */
   public PlayerDto addLinksToPlayer(Long playerId, Long clubId) throws IOException {
      this.add(linkTo(methodOn(PlayerController.class).getPlayerById(playerId)).withSelfRel());
      this.add(linkTo(methodOn(PlayerController.class).getPlayerImage(playerId)).withRel("image"));
      this.add(linkTo(methodOn(ClubController.class).getClubById(clubId)).withRel("club"));
      return this;
   }

   /**
    * Adiciona links (self e image) no JSON de {@link Club}
    *
    * @param playerId  Identificador do jogador.
    * @return Instância de {@link ClubDto} com os links adicionados.
    * @throws IOException Exceção lançada no método GetPlayerImage
    */
   public PlayerDto addLinks(Long playerId) throws IOException {
      this.add(linkTo(methodOn(PlayerController.class).getPlayerById(playerId)).withSelfRel());
      this.add(linkTo(methodOn(PlayerController.class).getPlayerImage(playerId)).withRel("image"));
      return this;
   }
}
