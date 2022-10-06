package com.lecheng.protectAnimals.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Integer id;
    private Integer commentid2;
    private Integer userid;
    private Integer dynamicsid;
    private Integer likes;
    private String username;
    private String discussion;
    private String head_img;
    private String create_time;
    private List<Comment> childrenComments;
}
