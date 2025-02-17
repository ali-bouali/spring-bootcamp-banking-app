package com.alibou.banking.contact;

import com.alibou.banking.user.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CONTACT")
public class contact {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String iban;
    @ManyToOne
    @JoinColumn(name = "user_id")
   private user user;
}
