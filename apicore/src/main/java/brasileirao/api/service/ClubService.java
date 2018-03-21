package brasileirao.api.service;

import brasileirao.api.dto.ClubDto;
import brasileirao.api.dto.CoachDto;
import brasileirao.api.dto.PlayerDto;
import brasileirao.api.exception.ServiceException;
import brasileirao.api.exception.ValidationException;
import brasileirao.api.persistence.Club;

import java.io.IOException;
import java.util.List;

/**
 * Classe de serviços da entidade <i>Club</i>.
 */
public interface ClubService {

  /***
   * Busca por um clube usando como critério seu identificador.
   *
   * @param id Identificador usado como critério de busca.
   * @return Instância do clube encontrado. Null caso o clube não seja encontrado.
   */
  Club findById(Long id);

  /**
   * Salva no banco uma instância da entidade <i>Club</i>.
   *
   * @param club Instância a ser salva no banco.
   * @return Instância salva.
   */
  Club save(Club club);

  /**
   * Retorna lista de todos os clubes existentes no banco.
   *
   * @return Iterable com a lista de clubes.
   */
  Iterable<Club> findAll();

  /**
   * Converte uma instância de <i>Club</i> ao seu respectivo DTO.
   *
   * @param club Instância da classe <i>Club</i>, que será convertida em DTO.
   * @return Instância de <i>ClubDto</i>
   */
  ClubDto convertClubToClubDto(Club club);


  /**
   * Obtém lista de todos os clubes disponíveis.
   *
   * @return Lista de {@link ClubDto} com os dados dos clubes;
   * @throws IOException Pode ser lançada ao adicionar os links no DTO de retorno.
   */
  List<ClubDto> getAllClubs() throws IOException, ValidationException, ServiceException;

  /**
   * Obtém os dados de um clube a partir do seu identificador.
   *
   * @param clubId O id do clube do qual deseja-se obter os dados.
   * @return Instância de {@link ClubDto} com os dados do clube encontrado. null caso o clube não
   *         seja encontrado.
   * @throws IOException Pode ser lançada ao adicionar os links no DTO de retorno.
   * @throws ValidationException Pode ser lançada ao adicionar os links no DTO de retorno.
   */
  ClubDto getClubById(Long clubId) throws IOException, ValidationException, ServiceException;

  /**
   * Obtém os dados do técnico de um clube específico.
   * @param clubId Identificador do clube no qual o técnico trabalha.
   * @return Instância de {@link CoachDto} com os dados do técnico encontrado. null caso o técnico
   * não seja encotrado.
   * @throws ServiceException Lançada caso o clube não seja encontrado.
   * @throws IOException Pode ser lançada ao adicionar os links no DTO.
   * @throws ValidationException Pode ser lançada ao adicionar os links no DTO.
   */
  CoachDto getClubCoach(Long clubId) throws ServiceException, IOException, ValidationException;

  /**
   * Obtém a lista de jogadores de um clube específico.
   *
   * @param clubId O identificador do clube do qual deseja-se obter a lista de jogadores.
   * @return Lista de {@link PlayerDto} com os dados dos jogadores encontrados.
   * @throws IOException Pode ser lançada no momento de adicioanr os links no DTO.
   * @throws ValidationException Lançada caso o id do clube seja inválido.
   * @throws ServiceException Lançada caso o clube não seja encontrado.
   */
  List<PlayerDto> getClubPlayers(Long clubId) throws ServiceException, IOException,
      ValidationException;

}
