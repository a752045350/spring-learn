package com.springboot.learn.bean;

import io.searchbox.annotations.JestId;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Getter
@Setter

public class Article {

    @JestId
    private Integer Id;
    private String name;
    private String author;
    private String title;
}
