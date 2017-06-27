package brasileirao.api.persistence;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

/**
 * Representa um técnico de um clube de futebol.
 */
@Entity
@Table(name = "COACH")
public class Coach extends Person {

   /**
    * Clube atual do técnico.
    */
   @OneToOne
   @JoinColumn(name = "CLUB_ID")
   private Club actualClub;

   public Club getActualClub() {
      return actualClub;
   }

   public void setActualClub(Club actualClub) {
      this.actualClub = actualClub;
   }

}
