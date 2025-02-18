package com.alibou.banking.entities.contact;

import com.alibou.banking.entities.shared.SharedEntity;
import com.alibou.banking.entities.users.Users;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CONTACTS")
public class ContactEntity extends SharedEntity {
    private String firstName;
    private String lastName;
    private String iban;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
}
