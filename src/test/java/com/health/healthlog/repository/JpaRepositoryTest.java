package com.health.healthlog.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.health.healthlog.domain.Article;
import com.health.healthlog.domain.Training;
import com.health.healthlog.domain.UserAccount;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@DisplayName("JPA 연결 테스트")
@SpringBootTest
public class JpaRepositoryTest {

    private ArticleRepository articleRepository;
    private TrainingRepository trainingRepository;
    private UserAccountRepository userAccountRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired TrainingRepository trainingRepository,
            @Autowired UserAccountRepository userAccountRepository) {
        this.articleRepository = articleRepository;
        this.trainingRepository = trainingRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void whenSelecting_thenWorksFine() {
        //when
        List<Article> articles = articleRepository.findAll();

        //then
        assertThat(articles).isNotNull().hasSize(6);
    }

    @DisplayName("select 테스트")
    @Test
    void whenSelectingById_thenWorksFine() {
        //when
        Article article = articleRepository.findById(14L).orElseThrow();
        List<Training> trainings = article.getTrainings();
        //then
        assertThat(trainings).isNotNull().hasSize(2);
    }

    @Transactional
    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorksFine() {
        // Given
        long previousCount = articleRepository.count();
        Article article = new Article(UserAccount.of("beomu", "pw", null, null, null), "new content");

        // When
        articleRepository.save(article);

        // Then
        assertThat(articleRepository.count()).isEqualTo(previousCount + 1);
    }

    @Transactional
    @DisplayName("update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {
        // Given
        Article article = articleRepository.findById(1L).orElseThrow(EntityNotFoundException::new);
        String updatedContent = "this is health log.";
        article.setContent(updatedContent);

        // When
        Article savedArticle = articleRepository.saveAndFlush(article);

        // Then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("content", updatedContent);
    }
    @Transactional
    @DisplayName("delete 테스트")
    @Test
    void givenTestData_whenDeleting_thenWorksFine() {
        // Given
        Article article = articleRepository.findById(14L).orElseThrow();

        long previousArticleCount = articleRepository.count();
        long previousTrainingCommentCount = trainingRepository.count();
        int deletedCommentsSize = article.getTrainings().size();

        // When
        articleRepository.delete(article);

        // Then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
        assertThat(trainingRepository.count()).isEqualTo(previousTrainingCommentCount - deletedCommentsSize);
    }
}
