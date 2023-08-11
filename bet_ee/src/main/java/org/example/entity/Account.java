package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "account", cascade = CascadeType.PERSIST)
    private List<Transaction> transactionList;

    @OneToMany(mappedBy = "account")
    private List<Invoice> invoices;

    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;


    private Boolean active;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "total_balance")
    private Double totalBalance;

    public Account(String userName, String email, String password, Double totalBalance) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.totalBalance = totalBalance;
    }
}
