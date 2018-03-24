package brasileirao.api.service;

import brasileirao.api.dto.CoachDto;
import brasileirao.api.exception.ServiceException;
import brasileirao.api.exception.ValidationException;
import brasileirao.api.persistence.Club;
import brasileirao.api.persistence.Coach;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

/**
 * Classe de serviços da entidade <i>Coach</i>.
 */
public interface CoachService {

  /**
   * Busca por um técnico usando como critério seu identificador.
   *
   * @param id Identificador usado como critério de busca.
   * @return Instância do técnico encontrado. <i>null</i> caso o técnico não seja encontrado.
   */
  Coach findById(Long id);

  /**
   * Persiste no banco uma instância da entidade <i>Coach</i>
   *
   * @param coach Instância a ser persistida.
   * @return Instância persistida
   * @see Coach
   */
  Coach save(Coach coach);

  /**
   * Retorna lista de todos os técnicos existentes no banco.
   *
   * @return Iterable com a lista de técnicos.
   */
  Iterable<Coach> findAll();

  /**
   * Busca por um ténico usando como critério seu clube atual.
   *
   * @param club Instância da classe <i>Club</i> a ser usada como critério.
   * @return Instância do técnico encontrado. <i>null</i> caso o técnico não seja encontrado.
   */
  Coach findByActualClub(Club club);

  /**
   * Converte uma instância de <i>Coach</i> ao seu respectivo DTO.
   *
   * @param coach Instância da classe <i>Coach</i>, que será convertida em DTO.
   * @return Instância de <i>ClubDto</i>
   */
  CoachDto convertCoachToDto(Coach coach);

  List<CoachDto> getAllCoaches() throws ServiceException, ValidationException, IOException;

  CoachDto getCoachById(Long coachId) throws IOException, ValidationException, ServiceException;

  ResponseEntity getCoachImage(Long coachId) throws ServiceException, IOException;
}
