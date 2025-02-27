package com.alibou.banking.common;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public class AbstractEntity {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Long createBy;
    private Long updateBy;

}
