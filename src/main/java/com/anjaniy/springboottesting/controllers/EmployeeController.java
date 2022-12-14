package com.anjaniy.springboottesting.controllers;

import com.anjaniy.springboottesting.models.Employee;
import com.anjaniy.springboottesting.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id){
        return employeeService.getEmployeeById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteEmployee(@PathVariable("id") Long id){
        employeeService.deleteEmployee(id);
        return "Employee deleted successfully!";
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee){
        return employeeService.getEmployeeById(id)
                .map(savedEmployee -> {
                    savedEmployee.setFirstName(employee.getFirstName());
                    savedEmployee.setLastName(employee.getLastName());
                    savedEmployee.setDepartmentName(employee.getDepartmentName());
                    savedEmployee.setDesignation(employee.getDesignation());
                    savedEmployee.setEmail(employee.getEmail());
                    savedEmployee.setContactNo(employee.getContactNo());

                    Employee updateEmployee = employeeService.updateEmployee(savedEmployee);
                    return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
