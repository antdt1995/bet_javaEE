package org.example.footballteam;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FootballTeamDTO {
    private Long id;
    private String name;
    private String league;
    private String manager;

}
