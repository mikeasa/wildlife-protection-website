package com.lecheng.protectAnimals.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtMe {
    private Integer id;
    private Integer myid;
    private Integer otherid;
    private String atme;
    private String create_time;
}
