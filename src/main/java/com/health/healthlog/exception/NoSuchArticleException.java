package com.health.healthlog.exception;

import jakarta.persistence.EntityNotFoundException;

public class NoSuchArticleException extends EntityNotFoundException {

    public NoSuchArticleException(String message) {
        super(message);
    }

    public NoSuchArticleException(Long id) {
        this("게시글이 없습니다 - articleId: " + id);
    }
}
