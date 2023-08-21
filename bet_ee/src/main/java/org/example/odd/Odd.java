package org.example.odd;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.example.invoicedetail.InvoiceDetail;
import org.example.matchresult.MatchResult;
import org.example.enumclass.ResultEnum;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "odd")
public class Odd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResultEnum oddType;

    @ManyToOne
    @JoinColumn(name = "match_result_id", nullable = false)
    private MatchResult matchResult;

    @OneToMany(mappedBy = "odd", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<InvoiceDetail> invoiceDetails;

    @Column(name = "odd_rate", nullable = false)
    private Double oddRate;

    @Column(name = "zet_score", nullable = false)
    private Double zetScore;

    @Column(name = "end_date", nullable = false)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDate;

    private Boolean active;
}
