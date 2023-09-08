package org.example.service;

import org.example.exception.EntityNotFoundException;
import org.example.footballmatch.FootballMatch;
import org.example.footballmatch.FootballMatchDAO;
import org.example.footballmatch.FootballMatchDTO;
import org.example.footballteam.FootballTeam;
import org.example.footballteam.FootballTeamDAO;
import org.example.matchresult.MatchResult;
import org.example.matchresult.MatchResultDAO;
import org.example.footballmatch.FootballMatchService;
import org.example.footballmatch.FootballMatchMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class FootballMatchServiceTest {
    @InjectMocks
    private FootballMatchService footballMatchService;
    @Mock
    private FootballMatchDAO footballMatchDAO;
    @Mock
    private FootballTeamDAO footballTeamDAO;
    @Mock
    private FootballMatchMapper footballMatchMapper;
    @Mock
    private MatchResultDAO matchResultDAO;
    FootballTeam MU = FootballTeam.builder()
            .id(1L)
            .name("MU")
            .league("EPL")
            .manager("ERIK")
            .build();
    FootballTeam Chelsea = FootballTeam.builder()
            .id(2L)
            .name("Chelsea")
            .league("EPL")
            .manager("POCHA").build();
    FootballMatch footballMatch = FootballMatch.builder()
            .homeTeam(MU)
            .awayTeam(Chelsea)
            .build();
    FootballMatch footballMatch1 = FootballMatch.builder()
            .homeTeam(MU)
            .awayTeam(Chelsea)
            .build();
    FootballMatchDTO footballMatchDTO = FootballMatchDTO.builder().homeTeamName("MU").awayTeamName("Chelsea").build();

    FootballMatchDTO footballMatchDTO1 = FootballMatchDTO.builder().homeTeamName("MU").awayTeamName("Chelsea").build();
    @Test
    void getAll() {
        List<FootballMatch> footballMatchList= List.of(footballMatch,footballMatch1);
        List<FootballMatchDTO> footballMatchDTOS=List.of(footballMatchDTO,footballMatchDTO1);
        when(footballMatchDAO.findAll()).thenReturn(footballMatchList);
        when(footballMatchMapper.toDTOList(footballMatchList)).thenReturn(footballMatchDTOS);
        List<FootballMatchDTO> footballMatchDTO1 = footballMatchService.getAll();
        assertEquals(footballMatchList.size(),footballMatchDTO1.size());
    }


        @Test
        void testCreateFootballMatch() throws EntityNotFoundException {
            // Arrange
            FootballMatchDTO footballMatchDTO = new FootballMatchDTO();
            Long homeId = 1L;
            Long awayId = 2L;
            FootballTeam homeTeam = new FootballTeam();
            FootballTeam awayTeam = new FootballTeam();
            FootballMatch footballMatch = new FootballMatch();
            MatchResult matchResult = new MatchResult();

            when(footballTeamDAO.findById(homeId)).thenReturn(Optional.of(homeTeam));
            when(footballTeamDAO.findById(awayId)).thenReturn(Optional.of(awayTeam));
            when(footballMatchDAO.save(any(FootballMatch.class))).thenReturn(footballMatch);
            when(matchResultDAO.save(any(MatchResult.class))).thenReturn(matchResult);
            when(footballMatchMapper.toDTO(footballMatch)).thenReturn(footballMatchDTO);

            // Act
            FootballMatchDTO createdFootballMatchDTO = footballMatchService.create(footballMatchDTO, homeId, awayId);

            // Assert
            assertNotNull(createdFootballMatchDTO);
            assertEquals(footballMatchDTO, createdFootballMatchDTO);
            verify(footballTeamDAO).findById(homeId);
            verify(footballTeamDAO).findById(awayId);
            verify(footballMatchDAO).save(any(FootballMatch.class));
            verify(matchResultDAO).save(any(MatchResult.class));
            verify(footballMatchMapper).toDTO(footballMatch);
        }

        @Test
        void testCreateFootballMatchWithInvalidHomeTeam() throws EntityNotFoundException {
            // Arrange
            FootballMatchDTO footballMatchDTO = new FootballMatchDTO();
            Long homeId = 1L;
            Long awayId = 2L;

            when(footballTeamDAO.findById(homeId)).thenReturn(Optional.empty());

            // Assert
            // EntityNotFoundException should be thrown
            assertThrows(EntityNotFoundException.class, () ->
                footballMatchService.create(footballMatchDTO, homeId, awayId)
            );
        }

        @Test
        public void testCreateFootballMatchWithInvalidAwayTeam() throws EntityNotFoundException {
            // Arrange
            FootballMatchDTO footballMatchDTO = new FootballMatchDTO();
            Long homeId = 1L;
            Long awayId = 2L;
            FootballTeam homeTeam = new FootballTeam();

            when(footballTeamDAO.findById(homeId)).thenReturn(Optional.of(homeTeam));
            when(footballTeamDAO.findById(awayId)).thenReturn(Optional.empty());

            // Act
            assertThrows(EntityNotFoundException.class, () -> footballMatchService.create(footballMatchDTO, homeId, awayId));

        }

    @Test
    void testUpdateWithValidInput() throws EntityNotFoundException {
        // Mocking the dependencies
        Long id = 1L;
        FootballMatch footballMatch = new FootballMatch();
        footballMatch.setId(id);
        footballMatch.setAwayTeam(MU);
        footballMatch.setHomeTeam(Chelsea);


        FootballMatchDTO footballMatchDTO = new FootballMatchDTO();
        footballMatchDTO.setHomeScore(2L);
        footballMatchDTO.setAwayScore(1L);

        MatchResult matchResult = new MatchResult();
        matchResult.setTotalScore(0L);
        matchResult.setCompleteStatus(false);
        footballMatch.setMatchResult(matchResult);

        when(footballMatchDAO.findById(id)).thenReturn(Optional.of(footballMatch));
        when(footballMatchDAO.update(footballMatch)).thenReturn(footballMatch);

        // Calling the method to be tested
        FootballMatchDTO updatedFootballMatchDTO = footballMatchService.update(footballMatchDTO, id);

        // Verifying the results
        assertEquals(footballMatch.getHomeScore(), updatedFootballMatchDTO.getHomeScore());
        assertEquals(footballMatch.getAwayScore(), updatedFootballMatchDTO.getAwayScore());

        verify(footballMatchDAO, times(1)).findById(id);
        verify(footballMatchDAO, times(1)).update(footballMatch);
    }

    @Test
    public void testUpdateWithInvalidId() throws EntityNotFoundException {
        // Mocking the dependencies
        Long id = 1L;
        FootballMatchDTO footballMatchDTO = new FootballMatchDTO();
        footballMatchDTO.setHomeScore(2L);
        footballMatchDTO.setAwayScore(1L);

        when(footballMatchDAO.findById(id)).thenReturn(Optional.empty());

        // Calling the method to be tested
        assertThrows(EntityNotFoundException.class, () -> footballMatchService.update(footballMatchDTO, id)); // This should throw EntityNotFoundException

        verify(footballMatchDAO, times(1)).findById(id);
        verify(footballMatchDAO, never()).update(any(FootballMatch.class));
    }

}

