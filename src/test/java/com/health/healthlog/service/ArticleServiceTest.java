package com.health.healthlog.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;

import com.health.healthlog.domain.Article;
import com.health.healthlog.dto.ArticleDto;
import com.health.healthlog.dto.ArticleWithTrainingsDto;
import com.health.healthlog.repository.ArticleRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@DisplayName("비즈니스 로직 -게시글")
@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {
    @InjectMocks
    private ArticleService sut;
    @Mock
    private ArticleRepository articleRepository;

    @DisplayName("게시글을 검색하면, 게시글 리스트를 반환")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnsArticleList() {
        // Given
        Pageable pageable = Pageable.ofSize(20);
        given(articleRepository.findAll(pageable)).willReturn(Page.empty());

        // When
        Page<ArticleDto> articles = sut.searchArticles(null, null, pageable);

        // Then
        assertThat(articles).isEmpty();
        then(articleRepository).should().findAll(pageable);
    }

    @DisplayName("게시글을 조회하면, 게시글을 반환한다.")
    @Test
    void givenArticleId_whenSearchingArticle_thenReturnsArticle() {
        // Given
        Article result = new Article("id 15");
        given(articleRepository.findById(any())).willReturn(Optional.of(result));

        // When
        ArticleDto article = sut.searchArticle(15L);

        // Then
        ArticleDto articleDto = ArticleDto.from(result);
        assertThat(article).isNotNull().isEqualTo(articleDto);
    }

    @DisplayName("게시글 정보를 입력하면, 게시글을 생성한다")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSavesArticle() {
        // Given
        given(articleRepository.save(any(Article.class))).willReturn(null);

        // When
        sut.saveArticle(ArticleDto.of(1L, "content", LocalDateTime.now()));

        // Then
        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("게시글의 ID와 수정 정보를 입력하면, 게시글을 수정한다")
    @Test
    void givenArticleIdAndModifiedInfo_whenUpdatingArticle_thenUpdatesArticle() {
        // Given
        Article article = new Article("article");
        given(articleRepository.getReferenceById(15L)).willReturn(article);

        // When
        sut.updateArticle(15L, ArticleDto.of("new content"));

        // Then
        then(articleRepository).should().getReferenceById(15L);
    }

    @DisplayName("게시글의 ID를 입력하면, 게시글을 삭제한다")
    @Test
    void givenArticleId_whenDeletingArticle_thenDeletesArticle() {
        // Given
        willDoNothing().given(articleRepository).deleteById(any());

        // When
        sut.deleteArticle(1L);

        // Then
        then(articleRepository).should().deleteById(1L);
    }

    @DisplayName("게시글 ID로 조회하면, 해당 게시글 트레이닝이 포함된 게시글 반환한다.")
    @Test
    void givenArticleId_whenSearchingArticleWithTrainings_thenReturnsArticleWithTrainings() {
        // Given
        Long articleId = 1L;
        Article article = new Article("content");
        given(articleRepository.findById(articleId)).willReturn(Optional.of(article));

        // When
        ArticleWithTrainingsDto dto = sut.getArticleWithTrainings(articleId);

        // Then
        assertThat(dto)
                .hasFieldOrPropertyWithValue("content", article.getContent());
        then(articleRepository).should().findById(articleId);
    }
}
