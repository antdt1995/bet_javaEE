package org.example.matchresult;

import lombok.*;
import org.example.enumclass.ResultEnum;
import org.example.footballmatch.FootballMatch;
import org.example.odd.Odd;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "match_result")
public class MatchResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "match_id")
    private FootballMatch match;

    @OneToMany
    @JoinColumn(name = "match_result_id")
    private List<Odd> oddsList;

    private Long totalScore;

    @Enumerated(EnumType.STRING)
    private ResultEnum resultEnum;

    @Column(name = "complete_status")
    private Boolean completeStatus;
}
