package com.lecheng.protectAnimals.service.impl;


import com.lecheng.protectAnimals.dao.LatAndLngDao;
import com.lecheng.protectAnimals.pojo.LatAndLng;
import com.lecheng.protectAnimals.service.LatAndLngService;
import com.lecheng.protectAnimals.utils.LatAndLong;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class LatAndLngServiceImpl implements LatAndLngService {
    @Autowired
    private LatAndLngDao latAndLngDao;
    //日志打印
    private static Logger logger = Logger.getLogger(LatAndLngServiceImpl.class);
    @Override
    public List<LatAndLng> getAddressInfo(LatAndLng latAndLng) {
        List<LatAndLng> addressInfo = null;
        try {
            addressInfo = latAndLngDao.getAddressInfo(latAndLng);
        } catch (Exception e) {
            logger.error("获取失败");
        }
        ArrayList<LatAndLng> results = new ArrayList<>();
            Double[] distance=new Double[addressInfo.size()];
            for (int i=0;i<addressInfo.size();i++){
                addressInfo.get(i).setDistance(LatAndLong.GetDistance
                        (latAndLng.getLat(),latAndLng.getLng(),addressInfo.get(i).getLat(),addressInfo.get(i).getLng()));
                distance[i]=addressInfo.get(i).getDistance();
            }
            Arrays.sort(distance);
        Double[] distance2=new Double[3];
        if (addressInfo.size()>=3){
            for (int i=0;i<3;i++){
                distance2[i]=distance[i];
            }
            for (int i=0;i<3;i++){
                for (LatAndLng ll:addressInfo
                ) {
                    if (ll.getDistance()==distance2[i]){
                        results.add(ll);
                        addressInfo.remove(ll);
                        break;
                    }
                }
            }
            return results;
        }else {
            for (int i=0;i<addressInfo.size();i++){
                distance2[i]=distance[i];
            }
            for (int i=0;i<addressInfo.size();i++){
                for (LatAndLng ll:addressInfo
                ) {
                    if (ll.getDistance()==distance2[i]){
                        results.add(ll);
                        addressInfo.remove(ll);
                        break;
                    }
                }
            }
            return results;
        }

    }
}
