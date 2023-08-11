package org.example.service;

import org.example.dao.FootballMatchDAO;
import org.example.dao.MatchResultDAO;
import org.example.mapper.FootballMatchMapper;
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