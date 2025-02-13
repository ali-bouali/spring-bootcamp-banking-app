package com.alibou.banking;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Contact {
    @Id
    private Integer id;
    private String rib;
    private String name;

    @ManyToOne
    User user;
}
