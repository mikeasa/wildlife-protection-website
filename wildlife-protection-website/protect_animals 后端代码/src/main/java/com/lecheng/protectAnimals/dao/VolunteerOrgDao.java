package com.lecheng.protectAnimals.dao;

import com.lecheng.protectAnimals.pojo.*;

import java.util.List;
import java.util.Map;

public interface VolunteerOrgDao {
    List<VolunteerOrg> getVolunteerOrg();
    List<VolunteerActivityEntrance> getV_A_EEntrance(String statute);
    VolunteerActivityEntrance getVolunteerActivity(Integer v_a_id);
    int joinVolunteerActivity(JoinVolunteerActivity joinVolunteerActivity);
    List<JoinVolunteerActivity> getMyActivityId(JoinVolunteerActivity joinVolunteerActivity);
    CheckVolunteerNumber getCurrentNumber(Integer id);
    List<VolunteerActivityEntrance> getVolunteerActivityById( List<JoinVolunteerActivity> myActivityIds);//批量查询
    List<VolunteerActivityEntrance> getAllV_A_EEntrance();
    int addMoneyById(Map map);
}
