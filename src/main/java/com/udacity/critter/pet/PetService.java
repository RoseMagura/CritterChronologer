package com.udacity.critter.pet;

import com.udacity.critter.user.customer.Customer;
import com.udacity.critter.user.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PetService {
    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Pet savePet(Pet pet) {
        Pet savedPet = petRepository.save(pet);
        Customer customer = savedPet.getCustomer();

        customer.addPet(savedPet);
        customerRepository.save(customer);
        return savedPet;
    }

    public Pet getPet(long petId) {
        return petRepository.getOne(petId);
    }

    public List<Pet> getAll() {
        return petRepository.findAll();
    }

    public List<Pet> findPetsByOwner(long ownerId) {
        return petRepository.getPetsByCustomer_Id(ownerId);
    }
}
