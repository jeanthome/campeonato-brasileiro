package brasileirao.api.dto.input;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import brasileirao.api.enums.ClubTypeEnum;
import brasileirao.api.enums.GoalTypeEnum;
import brasileirao.api.enums.HalfEnum;

/**
 * DTO com as informações de um gol a ser criado.
 */
public class GoalInputDto {

	/**
	 * Clube dono do gol. (HOMECLUB || VISITORCLUB)
	 *
	 * @see brasileirao.api.enums.ClubTypeEnum
	 */
	@NotNull
	private ClubTypeEnum clubType;

	/**
	 * Id do jogador que fez o gol.
	 */
	@NotNull
	private Long goalOwner;

	/**
	 * Tempo em que o gol foi marcado.
	 */
	@NotNull
	private HalfEnum half;

	/**
	 * Minuto em que o gol foi marcado.
	 */
	@Min(0)
	@NotNull
	private Long minute;

	/**
	 * Tipo de gol.
	 */
	@NotNull
	private GoalTypeEnum goalType;

	/**
	 * Título do gol.
	 */
	@NotNull
	private String title;

	/**
	 * Descrição do lance do gol.
	 */
	@NotNull
	private String description;

	public ClubTypeEnum getClubType() {
		return clubType;
	}

	public void setClubType(ClubTypeEnum clubType) {
		this.clubType = clubType;
	}

	public Long getGoalOwner() {
		return goalOwner;
	}

	public void setGoalOwner(Long goalOwner) {
		this.goalOwner = goalOwner;
	}

	public HalfEnum getHalf() {
		return half;
	}

	public void setHalf(HalfEnum half) {
		this.half = half;
	}

	public Long getMinute() {
		return minute;
	}

	public void setMinute(Long minute) {
		this.minute = minute;
	}

	public GoalTypeEnum getGoalType() {
		return goalType;
	}

	public void setGoalType(GoalTypeEnum goalType) {
		this.goalType = goalType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
