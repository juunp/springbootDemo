package com.judithpivoteau.TechnicalTest.controller;

import com.judithpivoteau.TechnicalTest.dto.PostDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {
    private final static  String GET_URI = "/api/v10/posts";
    private PostController postController;

    @MockBean
    RestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        postController = new PostController(restTemplate);
    }

    @Test
    public void getPostsTest() throws Exception {
        List<PostDto> postDtos = new ArrayList<>();
        PostDto postDto1 = new PostDto();
        postDto1.body = "bbbb";
        postDto1.id = 1L;
        postDto1.title = "title";
        postDto1.userId = 12L;
        postDtos.add(postDto1);
        when(restTemplate.getForObject(eq(PostController.GET), eq(List.class))).thenReturn(postDtos);
        this.mockMvc.perform(get(GET_URI))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void getPostsResponseEmptyTest() throws Exception {
        when(restTemplate.getForObject(eq(PostController.GET), eq(List.class))).thenReturn(Collections.emptyList());
        this.mockMvc.perform(get(GET_URI))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getPostsReturnClientHttpErrorTest() throws Exception {
        HttpClientErrorException exception = HttpClientErrorException.create("client exception", HttpStatus.BAD_REQUEST, "client error", null, null, null);
        when(restTemplate.getForObject(eq(PostController.GET), eq(List.class))).thenThrow(exception);
        this.mockMvc.perform(get(GET_URI))
                .andExpect(status().isServiceUnavailable());
    }

    @Test
    public void getPostsReturnServerHttpErrorTest() throws Exception {
        HttpClientErrorException exception = HttpClientErrorException.create("server exception", HttpStatus.SERVICE_UNAVAILABLE, "client error", null, null, null);
        when(restTemplate.getForObject(eq(PostController.GET), eq(List.class))).thenThrow(exception);
        this.mockMvc.perform(get(GET_URI))
                .andExpect(status().isServiceUnavailable());
    }
}
