package brasileirao.api.service.impl;

import brasileirao.api.dao.MatchDao;
import brasileirao.api.dto.MatchDto;
import brasileirao.api.persistence.Match;
import brasileirao.api.service.MatchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementação da classe de serviços da entidade {@link Match}.
 */
@Service
public class MatchServiceImpl implements MatchService {

   /**
    * Objeto de acesso de dados da classe {@link Match}.
    */
   @Autowired
   private MatchDao matchDao;

   @Override
   public Match save(Match match) {
      return this.matchDao.save(match);
   }

   /**
    * Retorna as partidas de uma dada rodada.
    *
    * @param roundNumber O número da rodada.
    * @return List com as partidas encontradas.
    */
   @Override
   public List<Match> getMatchesInRound(Long roundNumber) {
      return this.matchDao.findByRoundNumberOrderByKickOffAsc(roundNumber);
   }

   /**
    * Busca uma partida usando como critério o seu identifador.
    *
    * @param matchId Identificador da partida
    * @return Instância de {@link Match} se existe. null, caso contrário.
    */
   @Override
   public Match findById(Long matchId) {
      return this.matchDao.findById(matchId);
   }


   /**
    * Converte uma instância de <i>Match</i> ao seu respectivo DTO.
    *
    * @param match Instância da classe <i>Club</i>, que será convertida em DTO.
    * @return Instância de <i>ClubDto</i>
    */
   public MatchDto convertMatchToDto(Match match) {

      final ModelMapper modelMapper = new ModelMapper();
      final MatchDto matchDto = modelMapper.map(match, MatchDto.class);
      matchDto.setIdentificator(match.getId());
      matchDto.getHomeClub().setIdentificator(match.getHomeClub().getId());
      matchDto.getVisitorClub().setIdentificator(match.getVisitorClub().getId());
      return matchDto;
   }
}
