package com.udacity.critter.user;



import com.udacity.critter.schedule.Schedule;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Entity
//@Table(name = "user")
public class Employee extends User {
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "skills",joinColumns = @JoinColumn(name = "employee_id"))
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;
    @ElementCollection
    private Set<DayOfWeek> daysAvailable;
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

//    public Employee(Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable, Schedule schedule) {
//        this.skills = skills;
//        this.daysAvailable = daysAvailable;
//        this.schedule = schedule;
//    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

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
}
