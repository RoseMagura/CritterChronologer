package com.udacity.critter.pet;

import com.udacity.critter.user.customer.Customer;
import com.udacity.critter.user.customer.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Customer customer = customerService.getCustomerById(petDTO.getOwnerId());
        Pet pet = convertPetDTOToEntity(petDTO);
        pet.setCustomer(customer);
//        System.out.println("From Controller: " + pet.toString());
        Pet savedPet = petService.savePet(pet);
        return convertEntityToPetDTO(savedPet);
//        return petDTO;
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.getPet(petId);
        if (pet != null) {
        return convertEntityToPetDTO(pet);
        }
        return null;
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.getAll();
        List<PetDTO> petDTOS = new ArrayList<>();
        pets.forEach((pet) -> petDTOS.add(convertEntityToPetDTO(pet)));
        return petDTOS;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.findPetsByOwner(ownerId);
        List<PetDTO> petDTOS = new ArrayList<>();
        pets.forEach((pet) -> petDTOS.add(convertEntityToPetDTO(pet)));
        return petDTOS;
    }

    private static Pet convertPetDTOToEntity(PetDTO petDTO){
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }

    private static PetDTO convertEntityToPetDTO(Pet pet){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getCustomer().getId());
        return petDTO;
    }
    
}
