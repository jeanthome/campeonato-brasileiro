package brasileirao.api.dto;

import brasileirao.api.controller.ClubController;
import brasileirao.api.controller.CoachController;
import brasileirao.api.exception.ValidationException;
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
  protected Long id;

  /***
   * Nome de exibição do técnico.
   */
  protected String displayName;

  /***
   * Idade do tecnico.
   */
  protected Integer age;

  public void setId(Long id) {
    this.id = id;
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
   * @param clubId Id do clube no qual o Coach trabalha.
   * @return DTO com os links adicionados.
   * @throws IOException Excecao
   */
  public CoachDto addLinks(Long clubId) throws IOException, ValidationException {
    this.add(linkTo(methodOn(CoachController.class).getCoachById(this.id)).withSelfRel());
    this.add(linkTo(methodOn(CoachController.class).getCoachImage(this.id)).withRel("image"));

    if (clubId != null) {
      this.add(linkTo(methodOn(ClubController.class).getClubById(clubId.toString())).withRel(
          "actualClub"));
    }
    return this;
  }


}
