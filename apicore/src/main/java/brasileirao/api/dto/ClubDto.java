package brasileirao.api.dto;

import brasileirao.api.controller.ClubController;
import brasileirao.api.persistence.Club;
import org.springframework.hateoas.ResourceSupport;

import java.io.IOException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Define a classe DTO para a entidade <i>Club</i>. Possui somente os atributos que serão expostos
 * pela API.
 */
public class ClubDto extends ResourceSupport {

   /***
    * Id do clube.
    */
   private Long identificator;

   /***
    * Nome completo do clube.
    */
   private String fullName;

   /***
    * Nome reduzido do clube.
    */
   private String name;

   /***
    * Abreviação do nome do clube.
    */
   private String abbreviation;

   public Long getIdentificator() {
      return identificator;
   }

   public void setIdentificator(Long identificator) {
      this.identificator = identificator;
   }

   public String getFullName() {
      return fullName;
   }

   public void setFullName(String fullName) {
      this.fullName = fullName;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAbbreviation() {
      return abbreviation;
   }

   public void setAbbreviation(String abbreviation) {
      this.abbreviation = abbreviation;
   }


   /**
    * Adiciona links (self e escudo) no JSON de {@link Club}
    *
    * @param clubId Identificador do clube.
    * @return Instância de {@link ClubDto} com os links adicionados.
    * @throws IOException
    */
   public ClubDto addLinks(Long clubId) throws IOException {

      this.add(linkTo(methodOn(ClubController.class).getClubById(clubId)).withSelfRel());
      this.add(linkTo(methodOn(ClubController.class).getBadge(clubId)).withRel("badge"));
      this.add(linkTo(methodOn(ClubController.class).getCoachOfClub(clubId)).withRel("coach"));
      this.add(linkTo(methodOn(ClubController.class).getPlayers(clubId)).withRel("players"));
      return this;
   }

}
