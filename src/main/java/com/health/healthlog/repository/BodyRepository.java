package com.health.healthlog.repository;

import com.health.healthlog.domain.Body;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodyRepository extends JpaRepository<Body, Long> {
    List<Body> findAllByUserAccountUserId(String userId);
}
