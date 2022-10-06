package com.lecheng.protectAnimals.service;


import com.lecheng.protectAnimals.pojo.LatAndLng;

import java.util.List;

public interface LatAndLngService {
    List<LatAndLng> getAddressInfo(LatAndLng latAndLng);
}
