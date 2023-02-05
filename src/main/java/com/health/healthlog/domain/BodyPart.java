package com.health.healthlog.domain;

public enum BodyPart {
    BACK("등"), CHEST("가슴"), LEG("하체");
    private String name;

    BodyPart(String name) {
        this.name = name;
    }
}
