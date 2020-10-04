package com.udacity.critter.user;

import com.udacity.critter.user.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

//    public void addEmployeeSchedule(Set<DayOfWeek> daysAvailable, long employeeId) {
//        Optional<User> employee = userRepository.findById(employeeId);
//    }

}
