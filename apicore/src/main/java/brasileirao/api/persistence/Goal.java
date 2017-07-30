package brasileirao.api.persistence;

import brasileirao.api.enums.HalfEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Representa um gol em uma partida.
 */
@Entity
@Table(name = "GOAL")
public class Goal {

   /**
    * Id da entidade
    */
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   /**
    * Dono do gol
    */
   @NotNull
   @ManyToOne(fetch = FetchType.LAZY)
   private Player owner;

   /**
    * Minuto no qual o gol foi marcado
    */
   @Column(name = "MINUTE")
   @NotNull
   @Min(0)
   private Long minute;

   /**
    * O tempo no qual o gol foi marcado.
    */
   @NotNull
   @Column(name = "HALF")
   private HalfEnum half;

   /**
    * Flag que indica se o gol foi contra.
    */
   @Column(name = "IS_OWN_GOAL")
   private Boolean isOwnGoal;

   /**
    * Título do texto que descreve o gol.
    */
   @Column(name = "TITLE", length = 65)
   private String title;

   /**
    * Descrição do lance do gol.
    */
   @Column(name = "DESCRIPTION", length = 350)
   private String description;

   public Player getOwner() {
      return owner;
   }

   public void setOwner(Player owner) {
      this.owner = owner;
   }

   public Long getMinute() {
      return minute;
   }

   public void setMinute(Long minute) {
      this.minute = minute;
   }

   public HalfEnum getHalf() {
      return half;
   }

   public void setHalf(HalfEnum half) {
      this.half = half;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
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
