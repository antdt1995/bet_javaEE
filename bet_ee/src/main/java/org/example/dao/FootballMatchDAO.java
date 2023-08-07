package org.example.dao;

import org.example.entity.FootballMatch;

import javax.ejb.Stateless;
import java.util.Optional;

@Stateless
public class FootballMatchDAO extends BaseDAO<FootballMatch> {

    public FootballMatchDAO() {
        super(FootballMatch.class);
    }

    public Optional<FootballMatch> deleteById(Long id) {
        Optional<FootballMatch> footballMatch = findById(id);
        if (footballMatch.isPresent()) {
            em.remove(footballMatch.get());
            return footballMatch;
        }
        return Optional.empty();
    }
}
