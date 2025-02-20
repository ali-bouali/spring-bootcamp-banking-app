package com.alibou.banking.contact;



import com.alibou.banking.common.AbstractEntity;
import com.alibou.banking.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "CONTACTS")
public class Contact extends AbstractEntity {

    private String firstName;
    private String lastName;
    private String iban;
    @ManyToOne
    @JoinColumn(name = "user")
   private User user;
}
