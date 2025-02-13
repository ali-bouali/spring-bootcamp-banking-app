package com.alibou.banking.Entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Fraud {
    @Id
    @GeneratedValue
    private int fraudId;
    private String description;
    private int amount;
    private String date;
    private String type;
    private String status;
    @OneToOne(mappedBy = "fraud")
    private Transaction transaction;
}
