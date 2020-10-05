package com.udacity.critter.user.employee;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * Represents a request to find available employees by skills. Does not map
 * to the database directly.
 */
public class EmployeeRequestDTO {
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "skills", joinColumns = @JoinColumn(name = "employee_id"))
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;
    private LocalDate date;

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "EmployeeRequestDTO{" +
                "skills=" + skills +
                ", date=" + date +
                '}';
    }
}
