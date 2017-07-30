package brasileirao.api.dao;

import brasileirao.api.persistence.PlayerInMatch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerInMatchDao extends CrudRepository<PlayerInMatch, Long> {

}
