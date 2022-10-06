package com.lecheng.protectAnimals.dao;



import com.lecheng.protectAnimals.pojo.AdminReleaseEntrance;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface AdminReleaseDao {
    List<AdminReleaseEntrance> getAdminReleaseEntrance();
    AdminReleaseEntrance getAdminReleaseById(int id);
}
