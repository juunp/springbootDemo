package com.judithpivoteau.TechnicalTest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PostDto {
    public final Long userId;
    public final Long id;
    public final String title;
    public final String body;


    @JsonCreator
    @JsonIgnoreProperties(ignoreUnknown = true)
    public PostDto(
            @JsonProperty("userId") Long userId,
            @JsonProperty("id") Long id,
            @JsonProperty("title") String title,
            @JsonProperty("body") String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
