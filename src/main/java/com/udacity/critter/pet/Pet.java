package com.udacity.critter.pet;

import com.udacity.critter.schedule.Schedule;
import com.udacity.critter.user.customer.Customer;
import com.udacity.critter.user.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pet {
    @Id
    @GeneratedValue
    private long id;
    private PetType type;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private Customer customer;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "pet_schedules",
            joinColumns = @JoinColumn(name = "pet_id"),
            inverseJoinColumns = @JoinColumn(name = "schedule_id")
    )
//    @JoinColumn(name = "schedule_id")
    Set<Schedule> schedules;
    private LocalDate birthDate;
    private String notes;

    public Pet() {}

    public Pet(PetType type, String name, Long ownerId, LocalDate birthDate, String notes) {
        this.type = type;
        this.name = name;
        this.birthDate = birthDate;
        this.notes = notes;
    }

    public Set<Schedule> getSchedule() {
        return schedules;
    }

//    public void setSchedule(Set<Schedule> schedules) {
//        this.schedules = schedules;
//    }
    public void addSchedule(Schedule schedule) {
        if(schedules == null) {
            schedules = new HashSet<>();
        }
        schedules.add(schedule);
    }
    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
