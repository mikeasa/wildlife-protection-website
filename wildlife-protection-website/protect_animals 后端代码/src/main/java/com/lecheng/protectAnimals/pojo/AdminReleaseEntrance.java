package com.lecheng.protectAnimals.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminReleaseEntrance {
private Integer id;
private String title;
private String title_abstract;
private String create_time;
private String img_url;
private String author;
private String context;
private String type;
}
