package org.example.service;

import org.example.footballmatch.FootballMatchDAO;
import org.example.matchresult.MatchResultDAO;
import org.example.footballmatch.FootballMatchService;
import org.example.footballmatch.FootballMatchMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class})
class FootballMatchServiceTest {
    @InjectMocks
    private FootballMatchService footballMatchService;
    @Mock
    private FootballMatchDAO footballMatchDAO;
    @Mock
    private FootballMatchMapper footballMatchMapper;
    @Mock
    private MatchResultDAO matchResultDAO;

}