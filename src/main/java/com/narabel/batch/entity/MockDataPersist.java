package com.narabel.batch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "mock_data_persist")
public class MockDataPersist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    private BigDecimal amount;

    public MockDataPersist( String name, BigDecimal amount ) {
        this.name = name;
        this.amount = amount;
    }
}
