package brasileirao.api.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "SEASON")
public class Season {

   /**
    * Identificador da temporada.
    */
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   /**
    * Ano da temporada.
    */
   @NotNull
   @Column(name = "YEAR")
   private Long year;

   /**
    * Lista de clubes que participaram da temporada.
    */
   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(name = "SEASON_CLUB", joinColumns = {@JoinColumn(name = "SEASON_ID")},
           inverseJoinColumns = {@JoinColumn(name = "CLUB_ID")})
   private List<Club> clubList;

   /**
    * Lista dos desempenhos dos clubes.
    */
   @NotNull
   @OneToMany(fetch = FetchType.LAZY, mappedBy = "season")
   private List<Performance> performanceList;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getYear() {
      return year;
   }

   public void setYear(Long year) {
      this.year = year;
   }

   public List<Club> getClubList() {
      return clubList;
   }

   public void setClubList(List<Club> clubList) {
      this.clubList = clubList;
   }

   public List<Performance> getPerformanceList() {
      return performanceList;
   }

   public void setPerformanceList(List<Performance> performanceList) {
      this.performanceList = performanceList;
   }
}
