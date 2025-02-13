package com.alibou.banking.fraude;

import com.alibou.banking.transaction.Transaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FRAUDE")
public class Fraude {
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private String type;
    private String status;
    private Date date;
    private String name;
    @OneToOne
    private Transaction transaction;


}
