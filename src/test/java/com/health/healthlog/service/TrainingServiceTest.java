package com.health.healthlog.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.health.healthlog.domain.Article;
import com.health.healthlog.dto.ArticleDto;
import com.health.healthlog.dto.TrainingDto;
import com.health.healthlog.repository.ArticleRepository;
import com.health.healthlog.repository.TrainingRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("비즈니스 로직 - 트레이닝")
@ExtendWith(MockitoExtension.class)
class TrainingServiceTest {

    @InjectMocks
    private TrainingService sut;
    @Mock
    private TrainingRepository trainingRepository;


    @DisplayName("게시글을 조회하면, 게시글을 반환한다.")
    @Test
    void givenArticleId_whenSearchingArticle_thenReturnsArticle() {
        // Given
        Long id = 1L;
        given(trainingRepository.findByArticle_Id(id)).willReturn(List.of());

        // When
        List<TrainingDto> trainingDtos = sut.searchTrainings(id);

        // Then
        assertThat(trainingDtos).isNotNull();
    }

}