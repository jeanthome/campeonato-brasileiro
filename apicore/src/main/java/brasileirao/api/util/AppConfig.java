package brasileirao.api.util;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * Classe de configuração dos Beans.
 */
@Configuration
public class AppConfig {

   /***
    * Define a classe que mapeia uma objeto ao seu respectivo DTO.
    *
    * @return Instância da classe ModelMapper.
    */
   @Bean
   public ModelMapper modelMapper() {
      return new ModelMapper();
   }
}
