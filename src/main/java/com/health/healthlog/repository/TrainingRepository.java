package com.health.healthlog.repository;

import com.health.healthlog.domain.Training;
import org.springframework.data.repository.CrudRepository;

public interface TrainingRepository extends CrudRepository<Training, Integer> {
}
