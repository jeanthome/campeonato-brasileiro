package brasileirao.api.service;

import brasileirao.api.dto.SubstitutionDto;
import brasileirao.api.persistence.Substitution;

import java.util.List;

/**
 * Interface da classe de serviços da entidade {@link Substitution}
 */
public interface SubstitutionService {

  /**
   * Persiste no banco uma instância da entidade {@link Substitution}.
   *
   * @param substitution Instância a ser persistida.
   * @return Instância que foi persistida.
   */
  Substitution save(Substitution substitution);

   /**
    * Converte uma instância de {@link Substitution} em seu DTO {@link SubstitutionDto};
    *
    * @param substitution Instância a ser convertida.
    * @return Instância do DTO.
    */
  SubstitutionDto convertSubstitutionToSubstitutionDto(Substitution substitution);

   /**
    * Converte lista de {@link Substitution} em uma lista de {@link SubstitutionDto}.
    *
    * @param substitutionList Lista com as instâncias a serem convertidas.
    * @return Lista com os DTOs convertidos.
    */
  List<SubstitutionDto> convertSubstitutionListToSubstitutionDtoList(
      List<Substitution> substitutionList);
}
