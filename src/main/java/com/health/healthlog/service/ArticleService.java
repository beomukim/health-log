package com.health.healthlog.service;

import com.health.healthlog.domain.type.SearchType;
import com.health.healthlog.dto.ArticleDto;
import com.health.healthlog.dto.ArticleUpdateDto;
import com.health.healthlog.repository.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public List<ArticleDto> searchArticles(SearchType title, String search_keyword) {
        return articleRepository.findAll().stream().map(ArticleDto::from).toList();
    }

    @Transactional(readOnly = true)
    public ArticleDto searchArticle(long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId: " + articleId));
    }

    public void saveArticle(ArticleDto dto) {
    }

    public void updateArticle(long articleId, ArticleUpdateDto dto) {
    }

    public void deleteArticle(long articleId) {
    }
}
