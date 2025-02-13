package com.alibou.banking.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Contact {

    @Id
    @GeneratedValue
    private Integer RIB;
    private String ContactName;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user_c;


}
