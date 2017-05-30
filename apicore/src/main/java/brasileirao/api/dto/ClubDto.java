package brasileirao.api.dto;

import org.springframework.hateoas.ResourceSupport;

/***
 * Define a classe DTO para a entidade <i>Club</i> Possui somente os atributos que serão expostos
 * pela API.
 */
public class ClubDto extends ResourceSupport {

   /***
    * Id do clube.
    */
   private Long id;

   /***
    * Nome completo do clube.
    */
   private String fullName;

   /***
    * Nome reduzido do clube.
    */
   private String name;

   /***
    * Abreviação do nome do clube.
    */
   private String abbreviation;

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

}
