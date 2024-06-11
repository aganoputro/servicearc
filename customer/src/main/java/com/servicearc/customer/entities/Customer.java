package com.servicearc.customer.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @SequenceGenerator(
            name="customer_id_sequence",
            sequenceName = "customer_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence"
    )
    private Integer id;
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    private String phone;
    private String password;
    @Column(nullable = false, unique = true)
    private String uniqueKey;
    @PrePersist
    void prePersist() {
        uniqueKey = UUID.randomUUID().toString();
    }
}
