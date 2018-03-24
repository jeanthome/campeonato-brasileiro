package brasileirao.api.service.impl;

import brasileirao.api.dao.CoachDao;
import brasileirao.api.dto.CoachDto;
import brasileirao.api.enums.ServiceExceptionMessageEnum;
import brasileirao.api.exception.ServiceException;
import brasileirao.api.exception.ValidationException;
import brasileirao.api.helper.DateHelper;
import brasileirao.api.persistence.Club;
import brasileirao.api.persistence.Coach;
import brasileirao.api.persistence.Player;
import brasileirao.api.service.CoachService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Implementação da classe de serviços da entidade <i>Coach</i>.
 *
 * @see Coach
 */
@Service
public class CoachServiceImpl implements CoachService {

  /**
   * Objeto de acesso de dados da classe <i>Coach</i>.
   */
  @Autowired
  private CoachDao coachDao;

  /**
   * Busca por um técnico usando como critério seu identificador.
   *
   * @param id Identificador usado como critério de busca.
   * @return Instância do técnico encontrado. <i>null</i> caso o técnico não seja encontrado.
   */
  @Override
  public Coach findById(Long id) {
    return coachDao.findById(id);
  }

  /**
   * Persiste no banco uma instância da entidade <i>Coach</i>
   *
   * @param coach Instância a ser persistida.
   * @return Instância persistida
   * @see Coach
   */
  @Override
  public Coach save(Coach coach) {
    return this.coachDao.save(coach);
  }

  /**
   * Retorna lista de todos os técnicos existentes no banco.
   *
   * @return Iterable com a lista de técnicos.
   */
  @Override
  public Iterable<Coach> findAll() {
    return this.coachDao.findAll();
  }

  /**
   * Busca por um ténico usando como critério seu clube atual.
   *
   * @param club Instância da classe <i>Club</i> a ser usada como critério.
   * @return Instância do técnico encontrado. <i>null</i> caso o técnico não seja encontrado.
   */
  public Coach findByActualClub(Club club) {
    return this.coachDao.findByActualClub(club);
  }

  /**
   * Converte uma instância de <i>Coach</i> ao seu respectivo DTO.
   *
   * @param coach Instância da classe <i>Coach</i>, que será convertida em DTO.
   * @return Instância de <i>ClubDto</i>
   */
  @Override
  public CoachDto convertCoachToDto(Coach coach) {
    final ModelMapper modelMapper = new ModelMapper();
    final CoachDto coachDto = modelMapper.map(coach, CoachDto.class);

    /* Obtém e setta a idade do técnico. */
    final Date date = coach.getBirthDate();
    coachDto.setAge(DateHelper.convertDateToPeriod(date).getYears());
    coachDto.setIdentifier(coach.getId());
    return coachDto;
  }

  @Override
  public List<CoachDto> getAllCoaches() throws ServiceException, ValidationException, IOException {
    final Iterable<Coach> coachIterable = this.coachDao.findAll();
    final Iterator<Coach> coachIterator = coachIterable.iterator();

    final List<CoachDto> coachDtoList = new ArrayList<>();
    while (coachIterator.hasNext()) {
      final Coach coach = coachIterator.next();
      final CoachDto coachDto = this.convertCoachToDto(coach);
      coachDto.addLinks(coach);
      coachDtoList.add(coachDto);
    }

    return coachDtoList;
  }

  @Override
  public CoachDto getCoachById(Long coachId) throws IOException, ValidationException, ServiceException {

    final Coach coach = this.coachDao.findById(coachId);

    CoachDto coachDto = null;

    if (coach != null) {
      coachDto = this.convertCoachToDto(coach);
      coachDto.addLinks(coach);
    }

    return coachDto;
  }

  @Override
  public ResponseEntity getCoachImage(Long coachId) throws ServiceException, IOException {
    final Coach coach = this.coachDao.findById(coachId);

    if (coach == null) {
      throw new ServiceException(ServiceExceptionMessageEnum.COACH_NOT_FOUND.getMessage());
    }

    final ClassPathResource image =
        new ClassPathResource("/images/clubs/" + coach.getActualClub().getFolderName() + "/"
            + coach.getPhoto() + ".png");

    final InputStreamResource inputStreamResource;
    try {
      inputStreamResource = new InputStreamResource(image.getInputStream());
    } catch (IOException e) {
      throw new ServiceException(ServiceExceptionMessageEnum.COACH_IMAGE_NOT_FOUND.getMessage());
    }

    return ResponseEntity.ok().contentLength(image.contentLength())
        .contentType(MediaType.IMAGE_PNG).body(inputStreamResource);
  }
}

