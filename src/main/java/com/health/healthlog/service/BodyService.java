package com.health.healthlog.service;

import com.health.healthlog.domain.Body;
import com.health.healthlog.dto.UserAccountDto;
import com.health.healthlog.repository.BodyRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class BodyService {

    private final BodyRepository bodyRepository;

    @Transactional(readOnly = true)
    public List<Body> searchBodies(UserAccountDto userAccountDto) {
        return bodyRepository.findAllByUserAccountUserId(userAccountDto.userId());
    }
}
