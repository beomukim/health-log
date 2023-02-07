package com.health.healthlog.dto;

import com.health.healthlog.domain.Article;
import java.time.LocalDateTime;

public record ArticleDto(
        Long id,
        String content,
        LocalDateTime createdAt
) {
    public static ArticleDto of(Long id, String content, LocalDateTime createdAt) {
        return new ArticleDto(id, content, createdAt);
    }

    public static ArticleDto of(String content) {
        return new ArticleDto(null, content, null);
    }

    public static ArticleDto from(Article article) {
        return new ArticleDto(article.getId(), article.getContent(), article.getCreatedAt());
    }

    public Article toEntity() {
        return Article.of(content);
    }
}
