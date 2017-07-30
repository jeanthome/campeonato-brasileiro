package brasileirao.api.dto;

import brasileirao.api.enums.HalfEnum;

/**
 * DTO com as informações de um gol a ser criado.
 */
public class GoalInputDto {

   /**
    * Id da partida onde o gol foi marcado.
    */
   private Long match;

   /**
    * Clube dono do gol. (HOMECLUB || VISITORCLUB)
    *
    * @see brasileirao.api.enums.ClubTypeEnum
    */
   private String clubType;

   /**
    * Id do jogador que fez o gol.
    */
   private Long goalOwner;

   /**
    * Tempo em que o gol foi marcado.
    */
   private HalfEnum half;

   /**
    * Minuto em que o gol foi marcado.
    */
   private Long minute;

   /**
    * Flag que indica se é gol contra.
    */
   private Boolean isOwnGoal;

   /**
    * Título do gol.
    */
   private String title;

   /**
    * Descrição do lance do gol.
    */
   private String description;

   public Long getMatch() {
      return match;
   }

   public void setMatch(Long match) {
      this.match = match;
   }

   public String getClubType() {
      return clubType;
   }

   public void setClubType(String clubType) {
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

   public Boolean getOwnGoal() {
      return isOwnGoal;
   }

   public void setOwnGoal(Boolean ownGoal) {
      isOwnGoal = ownGoal;
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
