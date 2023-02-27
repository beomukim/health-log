package com.health.healthlog.exception;

import jakarta.persistence.EntityNotFoundException;

public class InvalidArticleException extends EntityNotFoundException {
    public InvalidArticleException(String message) {
        super(message);
    }
}
