package com.health.healthlog.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Table(indexes = {
        @Index(columnList = "email", unique = true)
})
@Entity
public class UserAccount extends BaseTimeEntity {
    @Id
    @Column(length = 50)
    private String userId;

    @Column(nullable = false)
    private String userPassword;

    @Column(length = 100)
    private String email;
    @Column(length = 100)
    private String nickname;
    private String memo;


    protected UserAccount() {
    }

    private UserAccount(String userId, String userPassword, String email, String nickname, String memo) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.email = email;
        this.nickname = nickname;
        this.memo = memo;
    }

    public static UserAccount of(String userId, String userPassword, String email, String nickname, String memo) {
        return new UserAccount(userId, userPassword, email, nickname, memo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserAccount that = (UserAccount) o;
        return Objects.equals(userId, that.userId) && Objects.equals(userPassword, that.userPassword)
                && Objects.equals(email, that.email) && Objects.equals(nickname, that.nickname)
                && Objects.equals(memo, that.memo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userPassword, email, nickname, memo);
    }
}