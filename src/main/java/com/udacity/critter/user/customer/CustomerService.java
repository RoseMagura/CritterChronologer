package com.udacity.critter.user.customer;

import com.udacity.critter.pet.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer findByPet(Pet pet) { return customerRepository.findUserByPet(pet);}

    public Customer getCustomerById(long ownerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(ownerId);
        return optionalCustomer.orElse(null);
    }
}
