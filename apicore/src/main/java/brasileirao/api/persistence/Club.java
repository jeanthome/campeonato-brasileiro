package brasileirao.api.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Representa um clube de futebol
 */
@Entity
@Table(name = "CLUB")
public class Club {

   /**
    * Id da entidade
    */
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   /**
    * Nome completo do clube. Ex.: Clube de Regatas Flamengo
    */
   @NotNull
   @Column(name = "FULL_NAME")
   private String fullName;

   /**
    * Nome reduzido do clube. Ex.: Flamengo
    */
   @NotNull
   @Column(name = "NAME")
   private String name;

   /**
    * Abreviação do nome do clube. Usado para mostrar os placares quando há pouco espaço na view.
    * Ex.: Fla, Cor
    */
   @NotNull
   @Column(name = "ABBREVIATION")
   private String abbreviation;

   /**
    * Nome do aqruivo contendo o escudo do clube.
    */
   @NotNull
   @Column(name = "IMAGE")
   private String image;

   /**
    * Breve história do clube.
    */
   @NotNull
   @Column(name = "HISTORY", length = 10000)
   private String history;

   /**
    * O mascote do clube. Ex.: Urubu, Porco
    */
   @NotNull
   @Column(name = "MASCOT")
   private String mascot;

   /**
    * Lista de jogadores do clube.
    */
   //@NotNull
   @OneToMany(fetch = FetchType.LAZY, mappedBy = "actualClub")
   private List<Player> playerList;

   /**
    * Atual técnico do clube.
    */
   @OneToOne(mappedBy = "actualClub")
   private Coach coach;

   /**
    * Atual presidente do clube.
    */
   @OneToOne(mappedBy = "club")
   private President president;

   /**
    * Nome do diretório onde os arquivos do clube serão armazenados.
    */
   private String folderName;

   /**
    * Contrutor vazio a ser utilizado nos Testes Unitários.
    */
   public Club() {
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFullName() {
      return fullName;
   }

   public void setFullName(String fullName) {
      this.fullName = fullName;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAbbreviation() {
      return abbreviation;
   }

   public void setAbbreviation(String abbreviation) {
      this.abbreviation = abbreviation;
   }

   public String getImage() {
      return image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public String getHistory() {
      return history;
   }

   public void setHistory(String history) {
      this.history = history;
   }

   public String getMascot() {
      return mascot;
   }

   public void setMascot(String mascot) {
      this.mascot = mascot;
   }

   public List<Player> getPlayerList() {
      return playerList;
   }

   public void setPlayerList(List<Player> playerList) {
      this.playerList = playerList;
   }

   public Coach getCoach() {
      return coach;
   }

   public void setCoach(Coach coach) {
      this.coach = coach;
   }

   public President getPresident() {
      return president;
   }

   public void setPresident(President president) {
      this.president = president;
   }

   public String getFolderName() {
      return folderName;
   }

   public void setFolderName(String folderName) {
      this.folderName = folderName;
   }
}
