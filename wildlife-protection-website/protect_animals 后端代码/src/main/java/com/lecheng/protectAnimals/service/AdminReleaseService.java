package com.lecheng.protectAnimals.service;



import com.lecheng.protectAnimals.pojo.AdminReleaseEntrance;

import java.util.List;

public interface AdminReleaseService {
    List<AdminReleaseEntrance> getAdminReleaseEntrance();
    AdminReleaseEntrance getAdminReleaseById(int id);
}
