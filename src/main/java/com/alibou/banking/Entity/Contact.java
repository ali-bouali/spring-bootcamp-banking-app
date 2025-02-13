package com.alibou.banking.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Contact {
    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String rib
    @ManyToOne
    private User userc;

}
