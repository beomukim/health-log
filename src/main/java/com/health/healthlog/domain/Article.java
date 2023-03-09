package com.health.healthlog.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Version;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Entity
public class Article extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OrderBy("id")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final List<Training> trainings = new ArrayList<>();

    @ManyToOne(optional = false)
    private UserAccount userAccount;

    @Version
    private Long version;

    @Setter
    private String content;

    protected Article() {}

    public Article(UserAccount userAccount, String content) {
        this.userAccount = userAccount;
        this.content = content;
    }

    public static Article of(UserAccount userAccount, String content) {
        return new Article(userAccount, content);
    }
}
