package com.health.healthlog.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import com.health.healthlog.config.TestSecurityConfig;
import com.health.healthlog.dto.security.BoardPrincipal;
import com.health.healthlog.service.BodyService;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@Import({TestSecurityConfig.class})
@ActiveProfiles("test")
@WebMvcTest(BodyController.class)
public class BodyControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BodyService bodyService;

    @WithMockUser
    @DisplayName("[view][GET] 바디 페이지 - 정상 호출")
    @Test
    void givenNothing_whenRequestingBodyView_thenReturnsBodyView() throws Exception {
        // Given
        BoardPrincipal boardPrincipal = createBoardPrincipal();

        // When & Then
        mvc.perform(
                        get("/body").with(user(boardPrincipal))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("body/index"))
                .andExpect(model().attributeExists("bodies"));
        then(bodyService).should().searchBodies(any());
    }

    private BoardPrincipal createBoardPrincipal() {
        return BoardPrincipal.of("beomu", "test", "e@mail.com", "nickname", "memo", Map.of());
    }

}
