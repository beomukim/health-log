package com.health.healthlog.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
@Entity
public class Body extends BaseTimeEntity {
    // - 체중
    // - 골격근
    // - 체지방
    // - 년, 월

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double muscleMass;

    private double fatMass;
}
