package com.health.healthlog.dto;

import com.health.healthlog.domain.BodyPart;
import com.health.healthlog.domain.Training;

public record TrainingDto(
        Long id,
        String name,
        Long articleId,
        BodyPart bodyPart,
        String category,
        Double weight,
        Integer number,
        Integer setCount
) {
    public static TrainingDto of(Long id, String name, Long articleId, BodyPart bodyPart, String category, Double weight,
                       Integer number, Integer setCount) {
        return new TrainingDto(id, name, articleId, bodyPart, category, weight, number, setCount);
    }

    public static TrainingDto from(Training entity) {
        return new TrainingDto(
                entity.getId(),
                entity.getName(),
                entity.getArticle().getId(),
                entity.getBodyPart(),
                entity.getCategory(),
                entity.getWeight(),
                entity.getNumber(),
                entity.getSetCount()
                );
    }
}
