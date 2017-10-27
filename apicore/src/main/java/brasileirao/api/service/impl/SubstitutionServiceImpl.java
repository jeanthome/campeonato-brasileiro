package brasileirao.api.service.impl;

import brasileirao.api.dao.SubstitutionDao;
import brasileirao.api.dto.SubstitutionDto;
import brasileirao.api.persistence.Substitution;
import brasileirao.api.service.SubstitutionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubstitutionServiceImpl implements SubstitutionService {

   /**
    * Instância do objeto de acesso de dados da entidade {@link Substitution}.
    */
   @Autowired
   private SubstitutionDao substitutionDao;

   @Override
   public Substitution save(Substitution substitution) {
      return this.substitutionDao.save(substitution);
   }

   @Override
   public SubstitutionDto convertSubstitutionToSubstitutionDto(Substitution substitution) {
      final ModelMapper modelMapper = new ModelMapper();
      final SubstitutionDto substitutionDto = modelMapper.map(substitution, SubstitutionDto.class);
      return substitutionDto;
   }

   @Override
   public List<SubstitutionDto> convertSubstitutionListToSubstitutionDtoList(List<Substitution> substitutionList) {
      final List<SubstitutionDto> substitutionDtoList = new ArrayList<>();
      for (Substitution substitution : substitutionList) {
         final SubstitutionDto dto = this.convertSubstitutionToSubstitutionDto(substitution);
         substitutionDtoList.add(dto);
      }
      return substitutionDtoList;
   }
}
