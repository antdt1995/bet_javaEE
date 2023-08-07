package org.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "football_match")
public class FootballMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "home_team_id", nullable = false)
    private FootballTeam homeTeam;

    @Column(name = "home_team_score")
    private Long homeScore;

    @ManyToOne
    @JoinColumn(name = "away_team_id", nullable = false)
    private FootballTeam awayTeam;

    @Column(name = "away_team_score")
    private Long awayScore;

    @Column(name = "total_score")
    private Long totalScore;

    @Column(name = "start_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDate;

    @OneToMany(mappedBy = "footballMatch", cascade = CascadeType.REMOVE)
    private List<Odd> odds;

    @Column(name = "complete_status")
    private Boolean completeStatus;

}
