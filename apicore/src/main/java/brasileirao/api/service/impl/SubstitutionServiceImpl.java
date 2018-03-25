package brasileirao.api.service.impl;

import brasileirao.api.dao.PlayerInMatchDao;
import brasileirao.api.dao.SubstitutionDao;
import brasileirao.api.dto.SubstitutionDto;
import brasileirao.api.dto.input.SubstitutionInputDto;
import brasileirao.api.enums.ServiceExceptionMessageEnum;
import brasileirao.api.exception.ServiceException;
import brasileirao.api.persistence.PlayerInMatch;
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

  /**
   * Classe de acesso de dados da entidade {@link brasileirao.api.persistence.PlayerInMatch}
   */
  @Autowired
  private PlayerInMatchDao playerInMatchDao;

  @Override
  public Substitution save(Substitution substitution) {
    return this.substitutionDao.save(substitution);
  }

  @Override
  public Substitution convertSubstitutionInputDtoToSubstitution(
      SubstitutionInputDto substitutionInputDto) throws ServiceException {
    final ModelMapper modelMapper = new ModelMapper();
    final Substitution substitution = modelMapper.map(substitutionInputDto, Substitution.class);
    substitution.setId(null);

    final PlayerInMatch playerWhoLeaves =
        this.playerInMatchDao.findOne(substitutionInputDto.getPlayerWhoLeaves());
    final PlayerInMatch playerWhoEnters =
        this.playerInMatchDao.findOne(substitutionInputDto.getPlayerWhoEnters());

    if (playerWhoEnters == null || playerWhoLeaves == null) {
      throw new ServiceException(ServiceExceptionMessageEnum.PLAYER_NOT_FOUND.name());
    }

    substitution.setPlayerWhoEnters(playerWhoEnters);
    substitution.setPlayerWhoLeaves(playerWhoLeaves);
    return substitution;
  }

  @Override
  public SubstitutionDto convertSubstitutionToSubstitutionDto(Substitution substitution) {
    final ModelMapper modelMapper = new ModelMapper();
    final SubstitutionDto substitutionDto = modelMapper.map(substitution, SubstitutionDto.class);
    return substitutionDto;
  }

  @Override
  public List<SubstitutionDto> convertSubstitutionListToSubstitutionDtoList(
      List<Substitution> substitutionList) {
    final List<SubstitutionDto> substitutionDtoList = new ArrayList<>();
    for (Substitution substitution : substitutionList) {
      final SubstitutionDto dto = this.convertSubstitutionToSubstitutionDto(substitution);
      substitutionDtoList.add(dto);
    }
    return substitutionDtoList;
  }
}
