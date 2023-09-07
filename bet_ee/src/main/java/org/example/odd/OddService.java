package org.example.odd;

import org.example.matchresult.MatchResultDAO;
import org.example.matchresult.MatchResult;
import org.example.exception.ErrorMessage;
import org.example.exception.EntityNotFoundException;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Stateless
public class OddService {
    @Inject
    private OddDAO oddDAO;

    @Inject
    private OddMapper oddMapper;
    @Inject
    private MatchResultDAO matchResultDAO;
    private static final Validator validator = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory()
            .getValidator();

    public List<OddDTO> getAll(){
        return oddMapper.toDTOS(oddDAO.getAll());
    }
    public OddDTO getById(Long id) throws EntityNotFoundException {
        Odd odd = oddDAO.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(ErrorMessage.ODD_NOT_FOUND_MSG_KEY,ErrorMessage.ODD_NOT_FOUND_MSG));
        return oddMapper.toDTO(odd);
    }

    public OddDTO create(OddDTO oddDTO, Long matchId) throws EntityNotFoundException {
        MatchResult matchResult = matchResultDAO.findByMatchId(matchId)
                .orElseThrow(()-> new EntityNotFoundException(ErrorMessage.FOOTBALL_MATCH_NOT_FOUND_MSG_KEY,ErrorMessage.FOOTBALL_MATCH_NOT_FOUND_MSG));
        validateOddConstraints(oddDTO);
        Odd odd = Odd.builder()
                .matchResult(matchResult)
                .oddRate(oddDTO.getOddRate())
                .oddType(oddDTO.getOddType())
                .zetScore(oddDTO.getZetScore())
                .endDate(matchResult.getMatch().getStartDate())
                .active(true)
                .build();
        return oddMapper.toDTO(oddDAO.save(odd));
    }
    public OddDTO findWinOdd(Long matchId){
        return oddMapper.toDTO(oddDAO.findWinOdd(matchId));
    }
    private void validateOddConstraints(OddDTO oddDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<OddDTO>> positionViolationSet = validator.validate(oddDTO);
        if (!positionViolationSet.isEmpty()) {
            throw new ConstraintViolationException(positionViolationSet);
        }
    }
}
