package brasileirao.api.persistence;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Classe abstrata que representa uma pessoa.
 */
@MappedSuperclass
public abstract class Person {

   /**
    * Id da pessoa. P.K. da tabela.
    */
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @NotNull
   @Column(name = "ID")
   protected Long id;

   /**
    * Primeiro nome da pessoa.
    */
   @NotNull
   @Column(name = "FIRST_NAME")
   protected String firstName;

   /**
    * Sobrenome da pessoa.
    */
   @NotNull
   @Column(name = "LAST_NAME")
   protected String lastName;

   /**
    * Nome de exibição.
    */
   @Column(name = "DISPLAY_NAME")
   protected String displayName;

   /**
    * Endereço do twitter da pessoa, caso ela tenha um.
    */
   @Column(name = "TWITTER")
   protected String twitter;


   /**
    * Data de nascimento.
    */
   @NotNull
   @Column(name = "BIRTH_DATE")
   protected Date birthDate;

   /**
    * Nacionalidade da pessoa.
    */
   @NotNull
   @Column(name = "NATIONALITY")
   protected String nationality;

   /**
    * Altura da pessoa.
    */
   @Column(name = "HEIGHT")
   protected BigDecimal height;

   /**
    * Nome do arquivo de imagem da pessoa.
    */
   @Column(name = "PHOTO")
   protected String photo;

   /**
    * Pequena biografia da pessoa.
    */
   @Column(name = "BIOGRAPHY", length = 10000)
   protected String biography;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getDisplayName() {
      return displayName;
   }

   public String getTwitter() {
      return twitter;
   }

   public void setTwitter(String twitter) {
      this.twitter = twitter;
   }

   public void setDisplayName(String displayName) {
      this.displayName = displayName;
   }

   public Date getBirthDate() {
      return birthDate;
   }

   public void setBirthDate(Date birthDate) {
      this.birthDate = birthDate;
   }

   public String getNationality() {
      return nationality;
   }

   public void setNationality(String nationality) {
      this.nationality = nationality;
   }

   public BigDecimal getHeight() {
      return height;
   }

   public void setHeight(BigDecimal height) {
      this.height = height;
   }

   public String getPhoto() {
      return photo;
   }

   public void setPhoto(String photo) {
      this.photo = photo;
   }

   public String getBiography() {
      return biography;
   }

   public void setBiography(String biography) {
      this.biography = biography;
   }

}
