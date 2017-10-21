package brasileirao.api.dto;

import brasileirao.api.enums.GoalTypeEnum;

/**
 * Classe para representação de um gol.
 */
public class GoalDto {

   /**
    * Jogador que fez o gol.
    */
   private PlayerMinDto owner;

   /**
    * Minuto em que o gol foi marcado.
    */
   private Long minute;

   /**
    * Tempo em que o gol foi marcado.
    */
   private String half;

   /**
    * Flag que indica se é gol contra.
    */
   private GoalTypeEnum goalType;

   /**
    * Título do gol.
    */
   private String title;

   /**
    * Breve descrição do lance que resultou no gol.
    */
   private String description;

   public PlayerMinDto getOwner() {
      return owner;
   }

   public void setOwner(PlayerMinDto owner) {
      this.owner = owner;
   }

   public Long getMinute() {
      return minute;
   }

   public void setMinute(Long minute) {
      this.minute = minute;
   }

   public String getHalf() {
      return half;
   }

   public void setHalf(String half) {
      this.half = half;
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
