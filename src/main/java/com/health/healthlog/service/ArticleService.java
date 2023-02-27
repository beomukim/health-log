package com.health.healthlog.service;

import com.health.healthlog.domain.Article;
import com.health.healthlog.domain.UserAccount;
import com.health.healthlog.domain.type.SearchType;
import com.health.healthlog.dto.ArticleDto;
import com.health.healthlog.dto.ArticleWithTrainingsDto;
import com.health.healthlog.exception.InvalidArticleException;
import com.health.healthlog.exception.NoSuchArticleException;
import com.health.healthlog.repository.ArticleRepository;
import com.health.healthlog.repository.UserAccountRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserAccountRepository userAccountRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
        if (searchKeyword == null || searchKeyword.isBlank()) {
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }
        //TODO : searchType 분류

        return articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from);
    }

    @Transactional(readOnly = true)
    public ArticleDto searchArticle(long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleDto::from)
                .orElseThrow(() -> new NoSuchArticleException(articleId));
    }

    @Transactional(readOnly = true)
    public ArticleWithTrainingsDto getArticleWithTrainings(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleWithTrainingsDto::from)
                .orElseThrow(() -> new NoSuchArticleException(articleId));
    }

    public void saveArticle(ArticleDto dto) {
        UserAccount userAccount = userAccountRepository.getReferenceById(dto.userAccountDto().userId());
        articleRepository.save(dto.toEntity(userAccount));
    }

    public void updateArticle(long articleId, ArticleDto dto) {
        try {
            Article article = articleRepository.getReferenceById(articleId);
            UserAccount userAccount = userAccountRepository.getReferenceById(dto.userAccountDto().userId());

            if (article.getUserAccount().equals(userAccount)) {
                if (dto.content() != null) {
                    article.setContent(dto.content());
                }
            }
        } catch (NullPointerException e) {
            throw new InvalidArticleException(e.getMessage());
        }
    }

    public void deleteArticle(long articleId, String userId) {
        articleRepository.deleteByIdAndUserAccount_UserId(articleId, userId);
    }
}
