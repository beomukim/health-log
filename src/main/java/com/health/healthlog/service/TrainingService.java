package com.health.healthlog.service;

import com.health.healthlog.dto.TrainingDto;
import com.health.healthlog.repository.TrainingRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class TrainingService {
    private final TrainingRepository trainingRepository;

    @Transactional(readOnly = true)
    public List<TrainingDto> searchTrainings(Long articleId) {
        return trainingRepository.findByArticle_Id(articleId)
                .stream()
                .map(TrainingDto::from)
                .toList();
    }

    public void saveTraining(TrainingDto dto) {
    }
}
