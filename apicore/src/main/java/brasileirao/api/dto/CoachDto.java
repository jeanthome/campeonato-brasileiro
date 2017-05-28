package brasileirao.api.dto;

/***
 * Define a classe DTO para a entidade <i>Coach</i> Possui somente os atributos que serão expostos
 * pela API.
 */
public class CoachDto {

   protected Long id;

   protected String displayName;

   protected String photo;

   protected Integer age;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getDisplayName() {
      return displayName;
   }

   public void setDisplayName(String displayName) {
      this.displayName = displayName;
   }

   public String getPhoto() {
      return photo;
   }

   public void setPhoto(String photo) {
      this.photo = photo;
   }

   public Integer getAge() {
      return age;
   }

   public void setAge(Integer age) {
      this.age = age;
   }
}
