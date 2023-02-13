package com.health.healthlog.dto;

import com.health.healthlog.domain.UserAccount;
import java.time.LocalDateTime;

public record UserAccountDto(
        String userId,
        String userPassword,
        String email,
        String nickname,
        String memo,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public static UserAccountDto of(String userId, String userPassword, String email, String nickname, String memo) {
        return new UserAccountDto(userId, userPassword, email, nickname, memo, null, null);
    }

    public static UserAccountDto of(String userId, String userPassword, String email, String nickname, String memo, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new UserAccountDto(userId, userPassword, email, nickname, memo, createdAt, modifiedAt);
    }

    public static UserAccountDto from(UserAccount entity) {
        return new UserAccountDto(
                entity.getUserId(),
                entity.getUserPassword(),
                entity.getEmail(),
                entity.getNickname(),
                entity.getMemo(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }

    public UserAccount toEntity() {
        return UserAccount.of(
                userId,
                userPassword,
                email,
                nickname,
                memo
        );
    }

}