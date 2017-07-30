package brasileirao.web.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Aplicação Spring Boot
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"brasileirao"})
@EntityScan(basePackages = {"brasileirao"})
@ComponentScan(basePackages = {"brasileirao"})
public class Application {

   /**
    * Método que executa a aplicação.
    *
    * @param args Lista de argumentos.
    */
   public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
   }

   
}
