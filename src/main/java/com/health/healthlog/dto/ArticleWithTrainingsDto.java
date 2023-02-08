package com.health.healthlog.dto;

import com.health.healthlog.domain.Article;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record ArticleWithTrainingsDto(
        Long id,
        List<TrainingDto> trainingDtos,
        String content,
        LocalDateTime createdAt
) {
    public static ArticleWithTrainingsDto of(Long id, List<TrainingDto> trainingDtos, String content, LocalDateTime createdAt) {
        return new ArticleWithTrainingsDto(id, trainingDtos, content, createdAt);
    }

    public static ArticleWithTrainingsDto from(Article entity) {
        return new ArticleWithTrainingsDto(
                entity.getId(),
                entity.getTrainings().stream()
                        .map(TrainingDto::from)
                        .collect(Collectors.toList()),
                entity.getContent(),
                entity.getCreatedAt()
        );
    }
}
