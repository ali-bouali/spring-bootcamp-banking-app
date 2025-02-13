package com.alibou.banking.transaction;

import com.alibou.banking.fraude.Fraude;
import com.alibou.banking.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TRANSACTION")
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private Double amount;
    private Date date;
    private String status;
    private String destinationIban;
    private String sourceIban;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    private Fraude fraude;

}
