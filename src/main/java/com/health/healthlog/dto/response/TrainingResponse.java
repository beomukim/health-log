package com.health.healthlog.dto.response;

import com.health.healthlog.domain.BodyPart;
import com.health.healthlog.dto.TrainingDto;

public record TrainingResponse(
        Long id,
        String name,
        Long articleId,
        BodyPart bodyPart,
        String category,
        Double weight,
        Integer number,
        Integer setCount
) {

    public static TrainingResponse of(Long id, String name, Long articleId, BodyPart bodyPart, String category, Double weight,
                                 Integer number, Integer setCount) {
        return new TrainingResponse(id, name, articleId, bodyPart, category, weight, number, setCount);
    }


    public static TrainingResponse from(TrainingDto dto) {
        return new TrainingResponse(
                dto.id(),
                dto.name(),
                dto.articleId(),
                dto.bodyPart(),
                dto.category(),
                dto.weight(),
                dto.number(),
                dto.setCount()
        );
    }
}
