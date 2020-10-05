package com.udacity.critter.user;

import com.udacity.critter.user.customer.Customer;
import com.udacity.critter.user.customer.CustomerDTO;
import com.udacity.critter.user.customer.CustomerService;
import com.udacity.critter.user.employee.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    CustomerService customerService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
         Customer customer = customerService.saveCustomer(convertCustomerDTOToEntity(customerDTO));
        return convertEntityToCustomerDTO(customer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        customers.forEach((customer) -> {
            customerDTOS.add(convertEntityToCustomerDTO(customer));
        });
        return customerDTOS;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeService.saveEmployee(convertEmployeeDTOToEntity(employeeDTO));
        return convertEntityToEmployeeDTO(employee);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return convertEntityToEmployeeDTO(employeeService.getEmployee(employeeId));
//        throw new UnsupportedOperationException();
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.updateSchedule(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        EmployeeRequest employeeRequest = convertEmployeeRequestDTOToEntity(employeeDTO);
        LocalDate date = employeeRequest.getDate();
        List<Employee> availableEmployees = employeeService.getAvailableEmployees(date.getDayOfWeek());
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        availableEmployees.forEach((employee) -> {
            if(employee.getSkills().equals(employeeRequest.getSkills())){
                employeeDTOS.add(convertEntityToEmployeeDTO(employee));
            }
        });
        return employeeDTOS;
    }

    private static Customer convertCustomerDTOToEntity(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    private static Employee convertEmployeeDTOToEntity(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }

    private static EmployeeRequest convertEmployeeRequestDTOToEntity(EmployeeRequestDTO employeeDTO){
        EmployeeRequest employeeRequest = new EmployeeRequest();
        BeanUtils.copyProperties(employeeDTO, employeeRequest);
        return employeeRequest;
    }

    private static CustomerDTO convertEntityToCustomerDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    private static EmployeeDTO convertEntityToEmployeeDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

}
