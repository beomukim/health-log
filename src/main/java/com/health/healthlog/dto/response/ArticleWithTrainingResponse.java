package com.health.healthlog.dto.response;

import com.health.healthlog.dto.ArticleWithTrainingsDto;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record ArticleWithTrainingResponse(
        Long id,
        String content,
        LocalDateTime createdAt,
        String userId,
        Set<TrainingResponse> trainingResponse
) {

    public static ArticleWithTrainingResponse of(Long id, String content, LocalDateTime createdAt,
                                                 String userId, Set<TrainingResponse> articleCommentResponses) {
        return new ArticleWithTrainingResponse(id, content, createdAt, userId, articleCommentResponses);
    }

    public static ArticleWithTrainingResponse from(ArticleWithTrainingsDto dto) {
        return new ArticleWithTrainingResponse(
                dto.id(),
                dto.content(),
                dto.createdAt(),
                dto.userAccountDto().userId(),
                dto.trainingDtos().stream().map(TrainingResponse::from).collect(Collectors.toSet())
        );
    }
}
