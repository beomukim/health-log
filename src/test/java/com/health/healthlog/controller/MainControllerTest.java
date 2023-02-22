package com.health.healthlog.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import com.health.healthlog.dto.security.BoardPrincipal;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@WebMvcTest(MainController.class)
class MainControllerTest {

    @Autowired
    private MockMvc mvc;

    @DisplayName("[view][GET] 루트 페이지 - 리다이렉트")
    @Test
    void givenNothing_whenRequestingRootView_thenReturnsArticleView() throws Exception {
        // Given
        BoardPrincipal boardPrincipal = createBoardPrincipal();

        // When & Then
        mvc.perform(get("/").with(user(boardPrincipal)))
                .andExpect(redirectedUrl("/articles"));
    }

    private BoardPrincipal createBoardPrincipal() {
        return BoardPrincipal.of("beomu", "test", "e@mail.com", "nickname", "memo", Map.of());
    }
}