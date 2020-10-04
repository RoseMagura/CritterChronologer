package com.udacity.critter.user.customer;



import com.udacity.critter.pet.Pet;
import com.udacity.critter.user.User;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Customer extends User {
    private String phoneNumber;
    private String notes;
    @OneToMany(mappedBy = "customer")
    private Set<Pet> pets;

//    public Customer() {}
//    public Customer(String phoneNumber, String notes, Set<Pet> pets) {
//        this.phoneNumber = phoneNumber;
//        this.notes = notes;
//        this.pets = pets;
//    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", notes='" + notes + '\'' +
                ", pets=" + pets +
                '}';
    }
}
