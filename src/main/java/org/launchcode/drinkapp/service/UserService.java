package org.launchcode.drinkapp.service;

import java.util.List;

import org.springframework.data.domain.Page;

import  org.launchcode.drinkapp.models.User;

public interface UserService {
//    void saveEmployee(User user);
    User getEmployeeById(int id);
//    void deleteEmployeeById(int id);
//    Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}