package com.lecheng.protectAnimals.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowOtherUser {
    private Integer id;
    private Integer user1id;
    private Integer user2id;
}
