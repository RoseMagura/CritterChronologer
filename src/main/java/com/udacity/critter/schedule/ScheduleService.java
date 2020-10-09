package com.udacity.critter.schedule;

import com.udacity.critter.pet.Pet;
import com.udacity.critter.user.customer.Customer;
import com.udacity.critter.user.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    public List<Schedule> getAll() {
        return scheduleRepository.findAll();
    }

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> findByPet(Pet pet) {
        return scheduleRepository.findAllByPets(pet);
    }

    public List<Schedule> findByEmployee(Employee employee) {
        return scheduleRepository.findByEmployees(employee);
    }

    public Schedule findById(long scheduleId) {
        return scheduleRepository.getOne(scheduleId);
    }
}
