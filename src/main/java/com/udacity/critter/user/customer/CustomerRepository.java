package com.udacity.critter.user.customer;

import com.udacity.critter.pet.Pet;
import com.udacity.critter.user.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("Select c from Customer c where :pet member of c.pets")
    Customer findUserByPet(Pet pet);
}
