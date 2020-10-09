package com.udacity.critter.schedule;



import com.udacity.critter.pet.Pet;
import com.udacity.critter.pet.PetService;
import com.udacity.critter.user.User;
import com.udacity.critter.user.customer.Customer;
import com.udacity.critter.user.customer.CustomerService;
import com.udacity.critter.user.employee.Employee;
import com.udacity.critter.user.employee.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = convertScheduleDTOToEntity(scheduleDTO);
        Schedule savedSchedule = scheduleService.save(schedule);
        List<Long> eIds = scheduleDTO.getEmployeeIds();
        List<Long> pIds = scheduleDTO.getPetIds();
        if(eIds != null){
            eIds.forEach((id -> {
                Employee e = employeeService.getEmployee(id);
                e.addSchedule(savedSchedule);
                employeeService.saveEmployee(e);
            }));
        }
        if(pIds != null){
            pIds.forEach((pId -> {
                Pet p = petService.getPet(pId);
                p.addSchedule(savedSchedule);
                petService.savePet(p);
            }));
        }
        return convertEntityToScheduleDTO(savedSchedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAll();
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        schedules.forEach((schedule) ->
                scheduleDTOList.add(convertEntityToScheduleDTO(schedule))
        );
        return scheduleDTOList;
    }

    @GetMapping("/{scheduleId}")
    public ScheduleDTO getSchedule(@PathVariable long scheduleId) {
        Schedule schedule = scheduleService.findById(scheduleId);
        return convertEntityToScheduleDTO(schedule);
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        Pet pet = petService.getPet(petId);
        List<Schedule> schedules =  scheduleService.findByPet(pet);
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        schedules.forEach((schedule) ->
                scheduleDTOList.add(convertEntityToScheduleDTO(schedule)));
        return scheduleDTOList;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        List<Schedule> schedules = scheduleService.findByEmployee(employee);
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        schedules.forEach((schedule) -> {
                scheduleDTOList.add(convertEntityToScheduleDTO(schedule));
        });
        return scheduleDTOList;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Pet> pets = petService.findPetsByOwner(customerId);
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        pets.forEach((pet) ->
                {  scheduleService.findByPet(pet).forEach(petSchedule ->
                        scheduleDTOList.add(convertEntityToScheduleDTO(petSchedule)));
                });
        return scheduleDTOList;
    }

    private Schedule convertScheduleDTOToEntity(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        Set<Employee> employees = new HashSet<>();
        List<Long> employeeIds = scheduleDTO.getEmployeeIds();
        List<Long> petIds = scheduleDTO.getPetIds();
        if(employeeIds != null) {
            employeeIds.forEach(employeeId ->
                    employees.add(employeeService.getEmployee(employeeId)));
        }
        Set<Pet> pets = new HashSet<>();
        if(petIds != null) {
            petIds.forEach(petId ->
                        pets.add(petService.getPet(petId)));
        }
        schedule.setPets(pets);
        schedule.setEmployees(employees);
        return schedule;
    }

    private static ScheduleDTO convertEntityToScheduleDTO(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        if(schedule.getEmployees() != null){
            scheduleDTO.setEmployeeIds(schedule.getEmployees()
                    .stream().map(User::getId).collect(Collectors.toList()));
        }
        if(schedule.getPets() != null){
            scheduleDTO.setPetIds(schedule.getPets()
                    .stream().map(Pet::getId).collect(Collectors.toList()));
        }
        return scheduleDTO;
    }
}
