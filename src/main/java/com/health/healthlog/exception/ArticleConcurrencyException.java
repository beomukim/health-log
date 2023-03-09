package com.health.healthlog.exception;

import org.springframework.dao.ConcurrencyFailureException;

public class ArticleConcurrencyException extends ConcurrencyFailureException {
    public ArticleConcurrencyException(String msg) {
        super(msg);
    }
}
