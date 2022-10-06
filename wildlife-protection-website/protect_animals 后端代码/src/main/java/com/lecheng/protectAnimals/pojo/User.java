package com.lecheng.protectAnimals.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer userid;
    private String uname;
    private String upwd;
    private String phonenumber;
    private String head_img;
    private String recommend;
    private Double score;
}
