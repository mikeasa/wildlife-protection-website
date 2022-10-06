package com.lecheng.protectAnimals.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private Integer questionid;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
}
