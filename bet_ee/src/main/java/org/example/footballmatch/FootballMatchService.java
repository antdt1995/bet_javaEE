package org.example.footballmatch;

import org.apache.commons.collections4.CollectionUtils;
import org.example.enumclass.ResultEnum;
import org.example.exception.EntityNotFoundException;
import org.example.exception.ErrorMessage;
import org.example.footballteam.FootballTeam;
import org.example.footballteam.FootballTeamDAO;
import org.example.matchresult.MatchResult;
import org.example.matchresult.MatchResultDAO;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Stateless
public class FootballMatchService {
    private static final Validator validator = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory()
            .getValidator();
    @Inject
    private FootballMatchDAO footballMatchDAO;
    @Inject
    private FootballMatchMapper footballMatchMapper;
    @Inject
    private MatchResultDAO matchResultDAO;
    @Inject
    private FootballTeamDAO footballTeamDAO;

    private static void verifyResult(FootballMatch footballMatch, Long homeScore, Long awayScore) {
        if (homeScore > awayScore) {
            footballMatch.getMatchResult().setResultEnum(ResultEnum.WIN);

        } else if (homeScore < awayScore) {
            footballMatch.getMatchResult().setResultEnum(ResultEnum.LOSE);

        } else {
            footballMatch.getMatchResult().setResultEnum(ResultEnum.DRAW);

        }
    }

    public List<FootballMatchDTO> getAll() {
        List<FootballMatch> footballMatchList = footballMatchDAO.findAll();
        return footballMatchMapper.toDTOList(footballMatchList);
    }

    public FootballMatchDTO getById(Long id) throws EntityNotFoundException {
        FootballMatch footballMatch = footballMatchDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessage.FOOTBALL_MATCH_NOT_FOUND_MSG_KEY, ErrorMessage.FOOTBALL_MATCH_NOT_FOUND_MSG));
        return footballMatchMapper.toDTO(footballMatch);
    }

    public FootballMatchDTO create(FootballMatchDTO footballMatchDTO, Long homeId, Long awayId) throws EntityNotFoundException {
        FootballTeam homeTeam = footballTeamDAO.findById(homeId).orElseThrow(() -> new EntityNotFoundException(ErrorMessage.FOOTBALL_TEAM_NOT_FOUND_MSG_KEY, ErrorMessage.FOOTBALL_TEAM_NOT_FOUND_MSG));
        FootballTeam awayTeam = footballTeamDAO.findById(awayId).orElseThrow(() -> new EntityNotFoundException(ErrorMessage.FOOTBALL_TEAM_NOT_FOUND_MSG_KEY, ErrorMessage.FOOTBALL_TEAM_NOT_FOUND_MSG));
        verifyInput(footballMatchDTO);

        FootballMatch footballMatch = FootballMatch.builder()
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .startDate(footballMatchDTO.getStartDate())
                .build();
        footballMatch = footballMatchDAO.save(footballMatch);

        MatchResult matchResult = new MatchResult();
        matchResult.setMatch(footballMatch);
        matchResultDAO.save(matchResult);

        return footballMatchMapper.toDTO(footballMatch);
    }

    @Transactional
    public FootballMatchDTO update(FootballMatchDTO footballMatchDTO, Long id) throws EntityNotFoundException {
        FootballMatch footballMatch = footballMatchDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessage.FOOTBALL_MATCH_NOT_FOUND_MSG_KEY, ErrorMessage.FOOTBALL_MATCH_NOT_FOUND_MSG));

        verifyInput(footballMatchDTO);

        Long homeScore = footballMatchDTO.getHomeScore();
        Long awayScore = footballMatchDTO.getAwayScore();

        footballMatch.setHomeScore(homeScore);
        footballMatch.setAwayScore(awayScore);
        Long totalScore = homeScore + awayScore;

        footballMatch.getMatchResult().setTotalScore(totalScore);
        footballMatch.getMatchResult().setCompleteStatus(true);

        verifyResult(footballMatch, homeScore, awayScore);

        return footballMatchMapper.toDTO(footballMatchDAO.update(footballMatch));
    }

    public void delete(Long id) throws EntityNotFoundException {
        FootballMatch footballMatch = footballMatchDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessage.FOOTBALL_MATCH_NOT_FOUND_MSG_KEY, ErrorMessage.FOOTBALL_MATCH_NOT_FOUND_MSG));

        footballMatchDAO.delete(footballMatch.getId());
    }

    public void verifyInput(FootballMatchDTO footballMatchDTO) {
        Set<ConstraintViolation<FootballMatchDTO>> violations = validator.validate(footballMatchDTO);
        if (CollectionUtils.isNotEmpty(violations)) {
            throw new ConstraintViolationException(violations);
        }
    }
}
