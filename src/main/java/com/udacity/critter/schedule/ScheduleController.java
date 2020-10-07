package com.udacity.critter.schedule;



import com.udacity.critter.pet.Pet;
import com.udacity.critter.pet.PetService;
import com.udacity.critter.user.customer.Customer;
import com.udacity.critter.user.customer.CustomerService;
import com.udacity.critter.user.employee.Employee;
import com.udacity.critter.user.employee.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    PetService petService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = convertScheduleDTOToEntity(scheduleDTO);
        Schedule savedSchedule = scheduleService.save(schedule);
        return convertEntityToScheduleDTO(savedSchedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAll();
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        schedules.forEach((schedule) -> {
                scheduleDTOList.add(convertEntityToScheduleDTO(schedule));
        });
        return scheduleDTOList;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        Pet pet = petService.getPet(petId);
        List<Schedule> schedules =  scheduleService.findByPet(pet);
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        schedules.forEach((schedule) -> scheduleDTOList.add(convertEntityToScheduleDTO(schedule)));
        return scheduleDTOList;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        List<Schedule> schedules = scheduleService.findByEmployee(employee);
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        schedules.forEach((schedule) -> scheduleDTOList.add(convertEntityToScheduleDTO(schedule)));
        return scheduleDTOList;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        throw new UnsupportedOperationException();
//        Customer customer = customerService.getCustomerById(customerId);
//        List<Pet> pets = petService.findPetsByOwner(customerId);
//        List<Schedule> schedules = new ArrayList<>();
//        List<Schedule> petSchedules = new ArrayList<>();
//        pets.forEach((pet) -> petSchedules.add(scheduleService.findByPet(pet)));
//        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
//        schedules.forEach((schedule) -> scheduleDTOList.add(convertEntityToScheduleDTO(schedule)));
//        return scheduleDTOList;
    }

    private Schedule convertScheduleDTOToEntity(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        Set<Employee> employees = new HashSet<>();
        List<Long> employeeIds = scheduleDTO.getEmployeeIds();
        List<Long> petIds = scheduleDTO.getPetIds();
        if(employeeIds != null) {
            employeeIds.forEach(employeeId -> employees.add(employeeService.getEmployee(employeeId)));
        }
        Set<Pet> pets = new HashSet<>();
        if(petIds != null) {
            petIds.forEach(petId ->pets.add(petService.getPet(petId)));
        }
        schedule.setPets(pets);
        schedule.setEmployees(employees);
        return schedule;
    }

    private ScheduleDTO convertEntityToScheduleDTO(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        List<Long> employeeIds = new ArrayList<>();
        if(schedule.getEmployees() != null){
        schedule.getEmployees().forEach((employee) -> employeeIds.add(employee.getId()));}
        List<Long> petIds = new ArrayList<>();
        if(schedule.getPets() != null){
        schedule.getPets().forEach((pet) -> petIds.add(pet.getId()));}
        scheduleDTO.setEmployeeIds(employeeIds);
        scheduleDTO.setPetIds(petIds);
        return scheduleDTO;
    }
}
