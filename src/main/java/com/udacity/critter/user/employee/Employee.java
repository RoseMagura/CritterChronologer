package com.udacity.critter.user.employee;



import com.udacity.critter.schedule.Schedule;
import com.udacity.critter.user.User;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee extends User {
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "skills", joinColumns = @JoinColumn(name = "employee_id"))
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "employee_days_available", joinColumns = @JoinColumn(name = "employee_id"))
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "employee_schedules",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "schedule_id")
    )
    Set<Schedule> schedules;

    public Employee(){}
    public Employee(Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable) {
        this.skills = skills;
        this.daysAvailable = daysAvailable;
    }

    public Set<Schedule> getSchedule() {
        return schedules;
    }
    public void addSchedule(Schedule schedule) {
        if (schedules == null) {
            schedules = new HashSet<>();
        }
        schedules.add(schedule);
    }
//    public void setSchedule(Set<Schedule> schedules) {
//        this.schedules = schedules;
//    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "skills=" + skills +
                ", daysAvailable=" + daysAvailable
                +
//                ", schedule=" + schedule +
                '}';
    }


}
