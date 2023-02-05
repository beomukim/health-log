package com.health.healthlog.repository;

import com.health.healthlog.domain.Body;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodyRepository extends JpaRepository<Body, Integer> {
}
