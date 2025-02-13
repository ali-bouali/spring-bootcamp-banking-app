package com.alibou.banking;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Transaction {
    @Id
    private Integer id;
    private Date date;
    private Double amount;
    private String ribReceiver;
    private String ribSender;
    @ManyToOne
    User user;

}
