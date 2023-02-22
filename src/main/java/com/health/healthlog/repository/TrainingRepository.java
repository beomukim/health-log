package com.health.healthlog.repository;

import com.health.healthlog.domain.Training;
import java.util.Arrays;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TrainingRepository extends JpaRepository<Training, Long> {
    List<Training> findByArticle_Id(Long articleId);
}
