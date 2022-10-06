package com.lecheng.protectAnimals.dao;



import com.lecheng.protectAnimals.pojo.LatAndLng;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface LatAndLngDao {
    List<LatAndLng> getAddressInfo(LatAndLng latAndLng);
}
