package brasileirao.api.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import brasileirao.api.dto.PlayerDto;
import brasileirao.api.dto.PlayerMinDto;
import brasileirao.api.dto.PlayerRegisterDto;
import brasileirao.api.exception.ServiceException;
import brasileirao.api.exception.ValidationException;
import brasileirao.api.persistence.Player;

/**
 * Classe de serviços da entidade <i>Player</i>.
 */
public interface PlayerService {

  /**
   * Busca um jogador usando como critério seu nome de exibição.
   *
   * @param displayName Nome de exibição do jogador a ser buscado.
   * @return Instância do jogador encontrado, <i>null</i> caso não encontre.
   */
  Player findByDisplayName(String displayName);

  /**
   * Busca por um jogador usando como critério seu identificador.
   *
   * @param playerId Identificador a ser usado como critério de busca.
   * @return Instância do jogador encontrado, <i>null</i> caso não encontre.
   */
  PlayerDto getPlayerById(Long playerId) throws ServiceException, ValidationException, IOException;

  /**
   * Persiste no banco uma instância da entidade <i>Player</i>
   *
   * @param player Instância a ser persistida.
   * @return Instância persistida
   * @see Player
   */
  Player save(Player player);

  /**
   * Retorna lista de todos os jogadores existentes no banco.
   *
   * @return Iterable com a lista de jogadores.
   */
  Iterable<Player> findAll();

  /**
   * Converte uma instância de {@link Player} ao seu respectivo DTO.
   *
   * @param player Instância da classe {@link Player}, que será convertida em DTO.
   * @return Instância de {@link PlayerDto}
   */
  PlayerDto convertPlayerToDto(Player player);

  /**
   * Converte uma instância de {@link Player} em seu DTO de informações mínimas.
   *
   * @param player Instância da classe {@link Player}, que será convertida em DTO.
   * @return Instância de {@link PlayerMinDto}
   */
  PlayerMinDto convertPlayerToMinDto(Player player);

  /**
   * Converte uma lista de {@link Player} em uma lista de {@link PlayerMinDto}.
   *
   * @param playerList A lista de {@link Player} a ser convertida.
   * @return Lista de {@link PlayerMinDto} com o resultado da conversão.
   */
  List<PlayerMinDto> convertPlayerListToPlayerMinDtoList(List<Player> playerList);

  /**
   * Converte DTO PlayerRegister em um objeto da entidade Player.
   *
   * @param playerRegisterDto DTO com os dados.
   * @return Instancia de {@link Player}.
   */
  Player convertPlayerRegisterDtoToPlayer(PlayerRegisterDto playerRegisterDto);

  /**
   * Retorna todos os jogadores existentes no banco.
   * 
   * @return Lista de {@link PlayerDto} com os jogadores encontrados.
   * @throws IOException Pode ser lançada no momento da adição dos links.
   * @throws ValidationException Pode ser lançada no momento da adição dos links.
   * @throws ServiceException Pode ser lançada no momento da adição dos links.
   */
  List<PlayerDto> getAllPlayers() throws IOException, ValidationException, ServiceException;

  /**
   * Obtém a imagem do jogador.
   *
   * @param playerId Id do jogador cuja imagem deseja-se obter.
   * @return ResponseEntity com a Stream que representa a imagem do jogador.
   * @throws ServiceException Lançada caso o registro do jogador não exista.
   * @throws IOException Pode ser lançada no momento de gerar a Stream.
   */
  ResponseEntity getPlayerImage(Long playerId) throws ServiceException, IOException;

}
