package com.udacity.critter.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {
    @Autowired
    PetRepository petRepository;

//    public Long createPet()
}
