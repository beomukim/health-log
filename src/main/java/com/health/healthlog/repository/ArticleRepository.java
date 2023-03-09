package com.health.healthlog.repository;

import com.health.healthlog.domain.Article;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.hibernate.annotations.OptimisticLock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    Article getReferenceById(Long id);

    Page<Article> findByContentContaining(String searchKeyword, Pageable pageable);

    void deleteByIdAndUserAccount_UserId(long articleId, String userId);
}
