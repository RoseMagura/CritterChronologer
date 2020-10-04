package com.udacity.critter.user.customer;

import com.udacity.critter.user.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
