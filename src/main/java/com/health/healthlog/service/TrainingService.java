package com.health.healthlog.service;

import com.health.healthlog.domain.Training;
import com.health.healthlog.dto.TrainingDto;
import com.health.healthlog.repository.ArticleRepository;
import com.health.healthlog.repository.TrainingRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class TrainingService {

    private final ArticleRepository articleRepository;
    private final TrainingRepository trainingRepository;

    @Transactional(readOnly = true)
    public List<Training> searchTraining(Long articleId) {
        return List.of();
    }

    public void saveTraining(TrainingDto dto) {
    }
}
