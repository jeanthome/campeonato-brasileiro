package brasileirao.api.dto;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.IOException;

import org.springframework.hateoas.ResourceSupport;

import brasileirao.api.controller.ClubController;
import brasileirao.api.exception.ServiceException;
import brasileirao.api.exception.ValidationException;
import brasileirao.api.persistence.Club;

/**
 * Define a classe DTO para a entidade <i>Club</i>. Possui somente os atributos que serão expostos
 * pela API.
 */
public class ClubDto extends ResourceSupport {

  /***
   * Id do clube.
   */
  private Long identifier;

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

  /**
   * A cor que representa o time, em hexadecimal.
   */
  private String color;

  public Long getIdentifier() {
    return identifier;
  }

  public void setIdentifier(Long identifier) {
    this.identifier = identifier;
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

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  /**
   * Adiciona links (self e escudo) no JSON de {@link Club}
   *
   * @param clubId Identificador do clube.
   * @return Instância de {@link ClubDto} com os links adicionados.
   * @throws IOException
   */
  public ClubDto addLinks(Long clubId) throws IOException, ValidationException, ServiceException {

    this.add(linkTo(methodOn(ClubController.class).getClubById(clubId.toString())).withSelfRel());
    this.add(linkTo(methodOn(ClubController.class).getBadge(clubId.toString())).withRel("badge"));
    this.add(linkTo(methodOn(ClubController.class).getClubCoach(clubId.toString()))
        .withRel("coach"));
    this.add(linkTo(methodOn(ClubController.class).getPlayers(clubId.toString()))
        .withRel("players"));
    return this;
  }

}
