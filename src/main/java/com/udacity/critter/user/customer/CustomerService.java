package com.udacity.critter.user.customer;

import com.udacity.critter.user.customer.Customer;
import com.udacity.critter.user.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }
}
