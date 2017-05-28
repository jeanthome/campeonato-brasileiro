package brasileirao.api.dto;

/***
 * Define a classe DTO para a entidade <i>Club</i> Possui somente os atributos que serão expostos
 * pela API.
 */
public class ClubDto {

   private Long id;

   private String fullName;

   private String name;

   private String abbreviation;

   private String image;

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
}
