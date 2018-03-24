package brasileirao.api.dto;

import brasileirao.api.controller.ClubController;
import brasileirao.api.controller.CoachController;
import brasileirao.api.exception.ServiceException;
import brasileirao.api.exception.ValidationException;
import brasileirao.api.persistence.Coach;
import org.springframework.hateoas.ResourceSupport;

import java.io.IOException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/***
 * Define a classe DTO para a entidade <i>Coach</i> Possui somente os atributos que serão expostos
 * pela API.
 */
public class CoachDto extends ResourceSupport {

  /***
   * Id do Coach.
   */
  protected Long identifier;

  /***
   * Nome de exibição do técnico.
   */
  protected String displayName;

  /***
   * Idade do tecnico.
   */
  protected Integer age;

  public Long getIdentifier() {
    return identifier;
  }

  public void setIdentifier(Long identifier) {
    this.identifier = identifier;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  /**
   * Adiciona os devidos links ao DTO de {@link brasileirao.api.persistence.Coach}
   * 
   * @param coach A instância do técnico cujos dados foram solicitados.
   * @return DTO com os links adicionados.
   * @throws IOException Excecao
   */
  public CoachDto addLinks(Coach coach) throws IOException, ValidationException, ServiceException {
    this.add(linkTo(methodOn(CoachController.class).getCoachById(coach.getId().toString())).withSelfRel());
    this.add(linkTo(methodOn(CoachController.class).getCoachImage(coach.getId().toString())).withRel("image"));

    if (coach.getActualClub() != null) {
      this.add(linkTo(methodOn(ClubController.class).getClubById(coach.getActualClub().getId().toString())).withRel(
          "actualClub"));
    }

    return this;
  }


}
