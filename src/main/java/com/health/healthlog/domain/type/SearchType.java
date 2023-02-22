package com.health.healthlog.domain.type;

public enum SearchType {
    CONTENT("내용"), TRAINING("트레이닝"), DATE("날짜");

    private final String description;

    SearchType(String description) {
        this.description = description;
    }
}
