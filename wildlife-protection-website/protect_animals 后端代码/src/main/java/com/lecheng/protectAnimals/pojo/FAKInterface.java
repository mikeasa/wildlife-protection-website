package com.lecheng.protectAnimals.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FAKInterface {
    private  Integer id;
    private String context;
    private String type;
    private String imgurl;
    private String fuwenben_context;
    private String title;
}
