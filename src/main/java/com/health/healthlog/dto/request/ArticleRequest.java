package com.health.healthlog.dto.request;

import com.health.healthlog.dto.ArticleDto;
import com.health.healthlog.dto.UserAccountDto;

public record ArticleRequest(
        String title,
        String content
) {

    public static ArticleRequest of(String title, String content) {
        return new ArticleRequest(title, content);
    }

    public ArticleDto toDto(UserAccountDto userAccountDto) {
        return ArticleDto.of(
                userAccountDto,
                title,
                content
        );
    }

}