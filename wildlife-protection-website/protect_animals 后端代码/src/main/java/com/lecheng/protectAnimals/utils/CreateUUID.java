package com.lecheng.protectAnimals.utils;

import java.util.UUID;

public class CreateUUID {
    public static String create(){
        return UUID.randomUUID().toString().substring(24);
    }
}
