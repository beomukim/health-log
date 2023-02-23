package com.health.healthlog.repository;

import com.health.healthlog.domain.Body;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BodyRepository extends JpaRepository<Body, Long> {
    List<Body> findAllByUserAccountUserId(String userId);
}
