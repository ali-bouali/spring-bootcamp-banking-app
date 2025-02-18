package com.alibou.banking.entities.shared;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public class SharedEntity {
@Id
@GeneratedValue
private Long id;
private LocalDateTime createdDate;
private LocalDateTime lastModifiedDate;
private String createdBy;
private String lastModifiedBy;
}
