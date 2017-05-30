package brasileirao.api.dto;

/***
 * Define a classe DTO para a entidade <i>Coach</i> Possui somente os atributos que serão expostos
 * pela API.
 */
public class CoachDto {

   /***
    * Id do Coach.
    */
   protected Long id;

   /***
    * Nome de exibição do técnico.
    */
   protected String displayName;

   /***
    * Nome do arquivo com a foto do técnico.
    */
   protected String photo;

   /***
    * Idade do tecnico.
    */
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
