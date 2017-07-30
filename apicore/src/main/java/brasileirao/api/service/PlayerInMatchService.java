package brasileirao.api.service;

import brasileirao.api.dto.PlayerMinDto;
import brasileirao.api.persistence.Player;
import brasileirao.api.persistence.PlayerInMatch;

import java.util.List;

/**
 * Define os métodos a serem implementados pela classe de serviço.
 */
public interface PlayerInMatchService {


   /**
    * Persiste no banco uma instância da entidade <i>PlayerInMatch</i>
    *
    * @param playerInMatch Instância a ser persistida.
    * @return Instância persistida
    * @see PlayerInMatch
    */
   PlayerInMatch save(PlayerInMatch playerInMatch);

   /**
    * Retorna uma lista de {@link PlayerInMatchService} usando uma lista de ids de {@link
    * brasileirao.api.persistence.Player}
    *
    * @param idList Listas com os ids dos jogadores
    * @return Lista de PlayerInMatch correspondente.
    */
   List<PlayerInMatch> getPlayerListByIdList(List<Long> idList);

   /**
    * Converte uma instancia de Player em uma de PlayerInMatch.
    *
    * @param player Instância de Player a ser convertida.
    * @return Instância de PlayerInMatch convertida.
    */
   PlayerInMatch convertPlayerToPlayerInMatch(Player player);


   /**
    * Converte uma instância de {@link PlayerInMatch} em seu DTO de informações mínimas.
    *
    * @param playerInMatch Instância da classe {@link PlayerInMatch}, que será convertida em DTO.
    * @return Instância de {@link PlayerMinDto}
    */
   PlayerMinDto convertPlayerInMatchToMinDto(PlayerInMatch playerInMatch);

   /**
    * Converte uma lista de PlayerInMatch em uma lista de PlayerMinDto.
    *
    * @param playerList Lista de PlayerInMatch a ser convertida.
    * @return Lista de PlayerMinDto convertida.
    */
   List<PlayerMinDto> convertPlayerInMatchListToPlayerMinDtoList(List<PlayerInMatch> playerList);
}
