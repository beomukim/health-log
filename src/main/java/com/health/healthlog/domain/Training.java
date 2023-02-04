package com.health.healthlog.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.IntSequenceGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import org.hibernate.annotations.Fetch;

@Getter
@Entity
public class Training {
    // 분류 (맨몸, 머신, 프리웨이트)
    // - 부위 (등, 가슴, 하체, etc...)
    // - 무게
    // - 개수
    // - 세트

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
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
