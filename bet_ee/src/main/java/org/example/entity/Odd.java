package org.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "odd")
public class Odd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "odd_type", nullable = false)
    private OddTypeEnum oddType;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private FootballMatch footballMatch;

    @OneToMany(mappedBy = "odd", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<InvoiceDetail> invoiceDetails;

    @Column(name = "odd_rate",nullable = false)
    private Double oddRate;

    @Column(name = "set_score",nullable = false)
    private Double setScore;

    @Column(name = "end_date",nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDate;

    private Boolean active;
}