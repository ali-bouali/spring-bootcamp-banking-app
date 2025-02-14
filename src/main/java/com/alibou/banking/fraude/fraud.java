package com.alibou.banking.fraude;

import com.alibou.banking.transaction.transaction;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FRAUD")
public class fraud {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private fraudType type;
    @Enumerated(EnumType.STRING)
    private fraudStatus status;
    private LocalDateTime date;
    @OneToOne
    private transaction transaction;


}
