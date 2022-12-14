package com.anjaniy.springboottesting.services;

import com.anjaniy.springboottesting.models.Employee;
import com.anjaniy.springboottesting.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {


    private final EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id){
        return employeeRepository.findById(id);
    }

    public Employee updateEmployee(Employee updatedEmployee){
        return employeeRepository.save(updatedEmployee);
    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }
}
