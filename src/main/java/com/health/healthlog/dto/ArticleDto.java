package com.health.healthlog.dto;

import com.health.healthlog.domain.Article;
import com.health.healthlog.domain.UserAccount;
import java.time.LocalDateTime;

public record ArticleDto(
        Long id,
        UserAccountDto userAccountDto,
        String content,
        LocalDateTime createdAt
) {
    public static ArticleDto of(Long id, UserAccountDto userAccountDto, String content, LocalDateTime createdAt) {
        return new ArticleDto(id, userAccountDto, content, createdAt);
    }

    public static ArticleDto of(String content) {
        return new ArticleDto(null,null, content, null);
    }

    public static ArticleDto from(Article article) {
        return new ArticleDto(
                article.getId(),
                UserAccountDto.from(article.getUserAccount()),
                article.getContent(),
                article.getCreatedAt());
    }

    public static ArticleDto of(UserAccountDto userAccountDto, String title, String content) {
        return new ArticleDto(null, userAccountDto, content, null);
    }

    public Article toEntity(UserAccount userAccount) {
        return Article.of(userAccount, content);
    }
}
