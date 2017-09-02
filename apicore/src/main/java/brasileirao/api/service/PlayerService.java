package brasileirao.api.service;

import brasileirao.api.dto.PlayerDto;
import brasileirao.api.dto.PlayerMinDto;
import brasileirao.api.dto.PlayerRegisterDto;
import brasileirao.api.persistence.Club;
import brasileirao.api.persistence.Player;
import org.modelmapper.ModelMapper;

import java.util.List;

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
    * @param id Identificador a ser usado como critério de busca.
    * @return Instância do jogador encontrado, <i>null</i> caso não encontre.
    */
   Player findById(Long id);

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

   List<PlayerMinDto> convertPlayerListToPlayerMinDtoList( List<Player> playerList);

   /**
    * Converte DTO PlayerRegister em um objeto da entidade Player.
    *
    * @param playerRegisterDto DTO com os dados.
    * @return Instancia de {@link Player}.
    */
    Player convertPlayerRegisterDtoToPlayer(PlayerRegisterDto playerRegisterDto);
}
