package com.judithpivoteau.TechnicalTest.controller;

import com.judithpivoteau.TechnicalTest.dto.PostDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v10")
public class PostController {

    public final static String GET = "/posts";

    private Logger logger = LoggerFactory.getLogger(PostController.class);

    private final RestTemplate postService;

    private static ModelMapper modelMapper = new ModelMapper();

    public PostController(RestTemplate postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getPosts() {
        logger.debug("GET /api/v10/posts ");
        try {
            List postList = postService.getForObject(GET, List.class);
            Type listType = new TypeToken<List<PostDto>>() {
            }.getType();
            assert postList != null;
            List<PostDto> postDtos = modelMapper.map(postList, listType);
            logger.debug("found {} posts from third-party", postDtos.size());
            if (!postDtos.isEmpty()) {
                return ResponseEntity.ok(postDtos.stream().limit(50L).collect(Collectors.toList()));
            }
            return ResponseEntity.status(204).build();
        } catch (HttpClientErrorException e) {
            logger.error("request to third-party is incorrect: {}", e.getMessage());
            return ResponseEntity.status(503).build();
        } catch (HttpServerErrorException | UnknownHttpStatusCodeException e) {
            logger.error("something went wrong on third-party server");
            return ResponseEntity.status(503).build();
        } catch (Exception e) {
            logger.error("Exception caught: {}", e.getMessage());
            return ResponseEntity.status(500).build();
        }


    }
}
