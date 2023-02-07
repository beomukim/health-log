package com.health.healthlog.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.health.healthlog.domain.Article;
import com.health.healthlog.domain.type.SearchType;
import com.health.healthlog.dto.ArticleDto;
import com.health.healthlog.repository.ArticleRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        List<Article> result = List.of(new Article("1"), new Article("2"), new Article("3"));
        given(articleRepository.findAll()).willReturn(result);

        // When
        List<ArticleDto> articles = sut.searchArticles(SearchType.CONTENT, "search keyword");

        // Then
        assertThat(articles).isNotNull().hasSize(2);
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
}
