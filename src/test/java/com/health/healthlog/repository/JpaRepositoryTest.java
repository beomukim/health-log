package com.health.healthlog.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.health.healthlog.domain.Article;
import com.health.healthlog.domain.Training;
import com.health.healthlog.domain.UserAccount;
import com.health.healthlog.dto.ArticleDto;
import com.health.healthlog.service.ArticleService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.ActiveProfiles;

@DisplayName("JPA 연결 테스트")
@Import(JpaRepositoryTest.TestJpaConfig.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaRepositoryTest {

    private ArticleRepository articleRepository;
    private TrainingRepository trainingRepository;
    private UserAccountRepository userAccountRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired TrainingRepository trainingRepository,
            @Autowired UserAccountRepository userAccountRepository
    ) {
        this.articleRepository = articleRepository;
        this.trainingRepository = trainingRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void whenSelectingArticle_thenWorksFine() {
        //when
        List<Article> articles = articleRepository.findAll();

        //then
        assertThat(articles).isNotNull().hasSize(5);
    }

    @DisplayName("select 테스트")
    @Test
    void whenSelectingArticleById_thenWorksFine() {
        //when
        Article article = articleRepository.findById(16L).orElseThrow();
        List<Training> trainings = article.getTrainings();
        //then
        assertThat(trainings).isNotNull().hasSize(0);
    }

    @DisplayName("select 테스트")
    @Test
    void whenSelectingTraining_thenWorksFine() {
        //when
        List<Training> trainings = trainingRepository.findAll();

        //then
        assertThat(trainings).isNotNull().hasSize(0);
    }

    @DisplayName("select 테스트")
    @Test
    void whenSelectingTrainingById_thenWorksFine() {
        //when
        List<Training> trainings = trainingRepository.findByArticle_Id(14L);
        //then
        assertThat(trainings).isNotNull().hasSize(0);
    }

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

    @DisplayName("update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {
        // Given
        Article article = articleRepository.findById(16L).orElseThrow(EntityNotFoundException::new);
        String updatedContent = "this is health log.";
        article.setContent(updatedContent);

        // When
        Article savedArticle = articleRepository.saveAndFlush(article);

        // Then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("content", updatedContent);
    }
    @DisplayName("delete 테스트")
    @Test
    void givenTestData_whenDeleting_thenWorksFine() {
        // Given
        Article article = articleRepository.findById(16L).orElseThrow();

        long previousArticleCount = articleRepository.count();
        long previousTrainingCommentCount = trainingRepository.count();
        int deletedCommentsSize = article.getTrainings().size();

        // When
        articleRepository.delete(article);

        // Then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
        assertThat(trainingRepository.count()).isEqualTo(previousTrainingCommentCount - deletedCommentsSize);
    }

    @EnableJpaAuditing
    @TestConfiguration
    static class TestJpaConfig {
        @Bean
        AuditorAware<String> auditorAware() {
            return () -> Optional.of("beomu");
        }
    }
}
