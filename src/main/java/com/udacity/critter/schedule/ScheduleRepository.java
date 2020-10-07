package com.udacity.critter.schedule;

import com.udacity.critter.pet.Pet;
import com.udacity.critter.user.customer.Customer;
import com.udacity.critter.user.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("Select s from Schedule s where :pet member of s.pets")
    List<Schedule> findByPet(Pet pet);

    @Query("Select s from Schedule s where :employee member of s.employees")
    List<Schedule> findByEmployee(Employee employee);

//    @Query("Select s from Schedule s where :customer member of s.customers")
//    List<Schedule> findByCustomer(Customer customer);
}
