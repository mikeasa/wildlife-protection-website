package com.lecheng.protectAnimals.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerOrg {
    private Integer id;
    private String org_name;
    private String introduce;
    private Date create_time;
    private String imgurl;
}
