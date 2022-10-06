package com.lecheng.protectAnimals.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerActivityEntrance {
    private Integer id;
    private Integer vorg_id;
    private String theme;
    private String img_url;
    private Date start_time;
    private Date end_time;
    private Integer number;
    private Integer current_number;
    private String title;
    private String context;
    private String area;
    private Double score;
    private Double money;
    private String statute;
}
