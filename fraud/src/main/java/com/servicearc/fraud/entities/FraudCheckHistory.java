package com.servicearc.fraud.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fraud")
public class FraudCheckHistory {
    @Id
    @SequenceGenerator(
            name="fraud_id_sequence",
            sequenceName = "fraud_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fraud_id_sequence"
    )
    private Integer id;
    private String customerId;
    private Boolean isFraudster;
    private LocalDateTime createdAt;
}
