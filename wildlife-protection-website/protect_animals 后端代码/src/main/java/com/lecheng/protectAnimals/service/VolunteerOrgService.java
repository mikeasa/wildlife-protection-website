package com.lecheng.protectAnimals.service;

import com.github.pagehelper.PageInfo;
import com.lecheng.protectAnimals.pojo.JoinVolunteerActivity;
import com.lecheng.protectAnimals.pojo.VolunteerActivityEntrance;
import com.lecheng.protectAnimals.pojo.VolunteerOrg;

import java.util.List;
import java.util.Map;

public interface VolunteerOrgService {
    List<VolunteerOrg> getVolunteerOrg();
    PageInfo<VolunteerActivityEntrance> getV_A_EEntrance(Integer page, Integer rows,String statute);
    VolunteerActivityEntrance getVolunteerActivity(Integer v_a_id);
    int joinVolunteerActivity(JoinVolunteerActivity joinVolunteerActivity);
    Map getMyVolunteerActivity(JoinVolunteerActivity joinVolunteerActivity);
    PageInfo<VolunteerActivityEntrance> getAllV_A_EEntrance(Integer page);
    boolean addMoneyById(Map map);
}
