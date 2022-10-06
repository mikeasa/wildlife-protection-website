package com.lecheng.protectAnimals.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Animal {
    private Integer id;
    private String name;
    private String img;
    private String introduce;
    private String species;
    private String tags;
    private String zoological_history;
    private String m_c;
    private String habit;
    private String life_habits;
    private String distribution_range;
}
