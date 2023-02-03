package com.health.healthlog.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public enum BodyPart {
    BACK("등"), CHEST("가슴"), LEG("하체");
    private String name;

    BodyPart(String name) {
        this.name = name;
    }
}
