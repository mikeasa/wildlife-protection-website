package com.lecheng.protectAnimals.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dynamics {
    private Integer id;
    private Integer authorid;
    private Integer likes;
    private String title;
    private String author;
    private String msg;
    private String content;
    private String img_url;
    private String create_time;
}
