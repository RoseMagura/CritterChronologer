package com.udacity.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Customer saveCustomer(Customer customer){
        return userRepository.save(customer);
    }
    public Employee saveEmployee(Employee employee){
        return userRepository.save(employee);
    }

}
