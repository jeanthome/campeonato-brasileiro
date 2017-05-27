package brasileirao.api.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Representa um presidente de um clube de futebol.
 */
@Entity
@Table(name = "PRESIDENT")
public class President extends Person {

   /**
    * O clube presidido pelo presidente.
    */
   @OneToOne
   @JoinColumn(name = "CLUB_ID")
   private Club club;

   /**
    * Data de inicio do mandato.
    */
   @Column(name = "START_MANDATE_DATE")
   private Date startMandateDate;


   /**
    * Data do final do mandato.
    */
   @Column(name = "END_MANDATE_DATE")
   private Date endMandateDate;

   public Club getClub() {
      return club;
   }

   public void setClub(Club club) {
      this.club = club;
   }

   public Date getStartMandateDate() {
      return startMandateDate;
   }

   public void setStartMandateDate(Date startMandateDate) {
      this.startMandateDate = startMandateDate;
   }

   public Date getEndMandateDate() {
      return endMandateDate;
   }

   public void setEndMandateDate(Date endMandateDate) {
      this.endMandateDate = endMandateDate;
   }

}
