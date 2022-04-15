package com.revature.videoGameLand.services;

import com.revature.videoGameLand.daos.DeptDAO;
import com.revature.videoGameLand.models.Dept;

import java.util.List;

public class DeptService {
    private final DeptDAO deptDAO;

    public DeptService(DeptDAO deptDAO) {
        this.deptDAO = deptDAO;
    }

    public DeptDAO getDeptDAO() {
        return deptDAO;
    }

    public boolean firstTimeCheck() {
        List<Dept> userList = deptDAO.findAll();
        System.out.println();
        if (userList.isEmpty()) {
            return true;
        }
        return false;
    }
}
