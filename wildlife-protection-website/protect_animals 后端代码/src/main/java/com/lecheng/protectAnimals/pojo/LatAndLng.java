package com.lecheng.protectAnimals.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LatAndLng {
    private Integer id;
    private String name;
    private String address;
    private String phone;
    private Double lat;
    private Double lng;
    private String province;
    private String city;
    private Double distance;
}
