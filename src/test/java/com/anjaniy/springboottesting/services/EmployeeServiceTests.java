package com.anjaniy.springboottesting.services;

import com.anjaniy.springboottesting.models.Employee;
import com.anjaniy.springboottesting.repositories.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee anjaniy;

    @BeforeEach
    public void setup(){
//        employeeRepository = Mockito.mock(EmployeeRepository.class);
//        employeeService = new EmployeeService(employeeRepository);
        anjaniy = Employee.builder()
                .id(1L)
                .firstName("Anjaniy")
                .lastName("Salekar")
                .departmentName("JAVA")
                .designation("SE")
                .email("anjaniy01salekar@gmail.com")
                .contactNo("8799535110")
                .build();
    }

    //JUnit test case to save employee - operation:
    @Test
    @DisplayName("JUnit Test Case To Save Employee - Operation")
    void givenEmployeeObject_whenSave_thenReturnSavedEmployeeObject(){

        //given: - precondition or setup

//        Employee anjaniy = Employee.builder()
//                .firstName("Anjaniy")
//                .lastName("Salekar")
//                .departmentName("JAVA")
//                .designation("SE")
//                .email("anjaniy01salekar@gmail.com")
//                .contactNo("8799535110")
//                .build();

        BDDMockito.given(employeeRepository.save(anjaniy)).willReturn(anjaniy);
//        System.out.println(employeeRepository);
//        System.out.println(employeeService);

        //when: - action or behavior that we are going to test
        Employee savedEmployee = employeeService.saveEmployee(anjaniy);
//        System.out.println(savedEmployee);


        //then: - verify the output
        Assertions.assertThat(savedEmployee).isNotNull();

    }

    //JUnit test case to get all employees - operation:
    @Test
    @DisplayName("JUnit Test Case To Get All Employees - Operation")
    void givenEmployeeList_whenFindAll_thenReturnEmployeeList(){

        //given: - precondition or setup
        Employee jay = Employee.builder()
                .firstName("Jay")
                .lastName("Shah")
                .departmentName("JAVA")
                .designation("SE")
                .email("jay101shah@gmail.com")
                .contactNo("7878969632")
                .build();

        BDDMockito.given(employeeRepository.findAll()).willReturn(List.of(anjaniy, jay));

        //when: - action or behavior that we are going to test
        List<Employee> employees = employeeService.getAllEmployees();

        //then: - verify the output
        Assertions.assertThat(employees).isNotNull();
        Assertions.assertThat(employees.size()).isEqualTo(2);
    }

    //JUnit test case to get all employees - operation (Negative scenario):
    @Test
    @DisplayName("JUnit Test Case To Get All Employees - Operation [Negative Scenario]")
    void givenEmptyEmployeeList_whenFindAll_thenReturnEmptyEmployeeList(){

        //given: - precondition or setup
        Employee jay = Employee.builder()
                .firstName("Jay")
                .lastName("Shah")
                .departmentName("JAVA")
                .designation("SE")
                .email("jay101shah@gmail.com")
                .contactNo("7878969632")
                .build();

        BDDMockito.given(employeeRepository.findAll()).willReturn(Collections.emptyList());

        //when: - action or behavior that we are going to test
        List<Employee> employees = employeeService.getAllEmployees();

        //then: - verify the output
        Assertions.assertThat(employees).isEmpty();
        Assertions.assertThat(employees.size()).isEqualTo(0);
    }

    //JUnit test case to get employee by id - operation:
    @Test
    @DisplayName("JUnit Test Case To Get Employee By ID - Operation")
    void givenEmployeeObject_whenFindById_thenReturnEmployeeObject(){

        //given: - precondition or setup
//            Employee anjaniy = Employee.builder()
//                    .firstName("Anjaniy")
//                    .lastName("Salekar")
//                    .departmentName("JAVA")
//                    .designation("SE")
//                    .email("anjaniy01salekar@gmail.com")
//                    .contactNo("8799535110")
//                    .build();

        BDDMockito.given(employeeRepository.findById(1L)).willReturn(Optional.of(anjaniy));

        //when: - action or behavior that we are going to test
        Employee savedEmployeeAnjaniyInDB = employeeRepository.findById(anjaniy.getId()).get();

        //then: - verify the output
        Assertions.assertThat(savedEmployeeAnjaniyInDB).isNotNull();
    }

    //JUnit test case to update employee - operation:
    @Test
    @DisplayName("JUnit Test Case To Update Employee - Operation")
    void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployeeObject(){

        //given: - precondition or setup
//            Employee anjaniy = Employee.builder()
//                    .firstName("Anjaniy")
//                    .lastName("Salekar")
//                    .departmentName("JAVA")
//                    .designation("SE")
//                    .email("anjaniy01salekar@gmail.com")
//                    .contactNo("8799535110")
//                    .build();

        BDDMockito.given(employeeRepository.save(anjaniy)).willReturn(anjaniy);
        anjaniy.setDepartmentName("DEVOPS");
        anjaniy.setDesignation("SSE");

        //when: - action or behavior that we are going to test
        Employee updatedEmployeeAnjaniy = employeeRepository.save(anjaniy);

        //then: - verify the output
        Assertions.assertThat(updatedEmployeeAnjaniy).isNotNull();
        Assertions.assertThat(updatedEmployeeAnjaniy.getDesignation()).isEqualTo("SSE");
        Assertions.assertThat(updatedEmployeeAnjaniy.getDepartmentName()).isEqualTo("DEVOPS");
    }

    //JUnit test case to delete employee - operation:
    @Test
    @DisplayName("JUnit Test Case To Delete Employee - Operation")
    void givenEmployeeObject_whenDelete_thenRemoveEmployee(){

        //given: - precondition or setup
//            Employee anjaniy = Employee.builder()
//                    .firstName("Anjaniy")
//                    .lastName("Salekar")
//                    .departmentName("JAVA")
//                    .designation("SE")
//                    .email("anjaniy01salekar@gmail.com")
//                    .contactNo("8799535110")
//                    .build();

        BDDMockito.willDoNothing().given(employeeRepository).deleteById(1L);

        //when: - action or behavior that we are going to test
        employeeService.deleteEmployee(anjaniy.getId());

        //then: - verify the output
        BDDMockito.verify(employeeRepository, Mockito.times(1)).deleteById(anjaniy.getId());
    }
}
