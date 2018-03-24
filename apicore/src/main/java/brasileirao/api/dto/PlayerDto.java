package brasileirao.api.dto;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.IOException;
import java.util.Map;

import brasileirao.api.exception.ServiceException;
import brasileirao.api.persistence.Player;
import org.springframework.hateoas.ResourceSupport;

import brasileirao.api.controller.ClubController;
import brasileirao.api.controller.PlayerController;
import brasileirao.api.exception.ValidationException;
import brasileirao.api.persistence.Club;

/**
 * Dto para a representação de um jogador.
 */
public class PlayerDto extends ResourceSupport {

  /**
   * Identificador do jogador.
   */
  private Long identifier;

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
   * Adiciona os links (self, image e actualClub) no DTO contendo as informações do jogador.
   *
   * @param player A instância do jogador cujos dados foram solicitados.
   * @return
   * @throws IOException Exceção dos métodos dos Controllers.
   * @throws ValidationException Exceção dos métodos dos Controllers.
   * @throws ServiceException Exceção dos métodos dos Controllers.
   */
  public PlayerDto addLinks(Player player) throws IOException, ValidationException,
      ServiceException {

    this.add(linkTo(methodOn(PlayerController.class).getPlayerById(player.getId().toString()))
        .withSelfRel());
    this.add(linkTo(methodOn(PlayerController.class).getPlayerImage(player.getId().toString()))
        .withRel("image"));

    final Club actualClub = player.getActualClub();
    if (player.getActualClub() != null) {
      this.add(linkTo(methodOn(ClubController.class).getClubById(actualClub.getId().toString()))
          .withRel("actualClub"));
    }
    return this;
  }
}
