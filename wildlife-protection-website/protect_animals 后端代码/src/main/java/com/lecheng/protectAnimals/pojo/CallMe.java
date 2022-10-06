package com.lecheng.protectAnimals.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @auther QiuShangcheng
 * @create 2021/4/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CallMe {
    private Integer id;
    private String OtherName;
    private String callMe;
    private String create_time;
}
