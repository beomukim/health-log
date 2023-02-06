package com.health.healthlog.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Entity
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(optional = false)
    private Article article;

    @Enumerated(EnumType.STRING)
    private BodyPart bodyPart;

    private String category;

    private Double weight;

    private Integer number;

    @Column(name = "counting")
    private Integer setCount;

    protected Training() {}
}
