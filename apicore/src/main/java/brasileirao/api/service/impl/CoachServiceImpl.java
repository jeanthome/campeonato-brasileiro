package brasileirao.api.service.impl;

import brasileirao.api.dao.CoachDao;
import brasileirao.api.dto.CoachDto;
import brasileirao.api.helper.DateHelper;
import brasileirao.api.persistence.Club;
import brasileirao.api.persistence.Coach;
import brasileirao.api.service.CoachService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

      /*Obtem e setta a idade do técnico.*/
      final Date date = coach.getBirthDate();
      coachDto.setAge(DateHelper.convertDateToPeriod(date).getYears());
      return coachDto;
   }
}
