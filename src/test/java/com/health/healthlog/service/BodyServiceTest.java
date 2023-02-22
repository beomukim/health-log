package com.health.healthlog.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.health.healthlog.domain.Body;
import com.health.healthlog.dto.UserAccountDto;
import com.health.healthlog.repository.BodyRepository;
import com.health.healthlog.repository.UserAccountRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("비즈니스 로직 - 바디")
@ExtendWith(MockitoExtension.class)
class BodyServiceTest {

    @InjectMocks
    private BodyService sut;
    @Mock
    private BodyRepository bodyRepository;
    @Mock
    private UserAccountRepository userAccountRepository;

    @DisplayName("게시글을 검색하면, 게시글 리스트를 반환")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnsArticleList() {
        // Given
        UserAccountDto userAccountDto = createUserAccountDto();
        given(bodyRepository.findAllByUserAccountUserId(userAccountDto.userId())).willReturn(List.of());

        // When
        List<Body> bodies = sut.searchBodies(userAccountDto);

        // Then
        assertThat(bodies).isNotNull();
        then(bodyRepository).should().findAllByUserAccountUserId(userAccountDto.userId());
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of("beomu", "pw", "e@mail.com", "nickname", "memo");
    }

}