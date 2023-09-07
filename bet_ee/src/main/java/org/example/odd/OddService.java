package org.example.odd;

import org.example.matchresult.MatchResultDAO;
import org.example.matchresult.MatchResult;
import org.example.exception.ErrorMessage;
import org.example.exception.EntityNotFoundException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class OddService {
    @Inject
    private OddDAO oddDAO;

    @Inject
    private OddMapper oddMapper;
    @Inject
    private MatchResultDAO matchResultDAO;

    public List<OddDTO> getAll(){
        return oddMapper.toDTOS(oddDAO.getAll());
    }
    public OddDTO getById(Long id) throws EntityNotFoundException {
        Odd odd = oddDAO.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(ErrorMessage.ODD_NOT_FOUND_MSG_KEY,ErrorMessage.ODD_NOT_FOUND_MSG));
        return oddMapper.toDTO(odd);
    }

    public OddDTO create(OddDTO oddDTO, Long matchId) throws EntityNotFoundException {
        MatchResult matchResult = matchResultDAO.findById(matchId)
                .orElseThrow(()-> new EntityNotFoundException(ErrorMessage.FOOTBALL_MATCH_NOT_FOUND_MSG_KEY,ErrorMessage.FOOTBALL_MATCH_NOT_FOUND_MSG));
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
}
