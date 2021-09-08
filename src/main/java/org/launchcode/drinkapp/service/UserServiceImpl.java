package org.launchcode.drinkapp.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import org.launchcode.drinkapp.models.User;
import org.launchcode.drinkapp.models.data.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository UserRepository;
/*
    @Override
    public List<User> getAllEmployees() {
        return (List<User>) UserRepository.findAll();
    }

    @Override
    public void saveEmployee(User employee) {
        this.UserRepository.save(employee);
    }


 */
    @Override
    public User getEmployeeById(int id) {
        Optional<User> optional = UserRepository.findById(id);
        User employee = null;
        if (optional.isPresent()) {
            employee = optional.get();
        } else {
            throw new RuntimeException(" Employee not found for id :: " + id);
        }
        return employee;
    }
/*
    @Override
    public void deleteEmployeeById(long id) {
        this.UserRepository.deleteById((int) id);
    }

    @Override
    public Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return (Page<User>) this.UserRepository.findAll(pageable);
    }

 */
}