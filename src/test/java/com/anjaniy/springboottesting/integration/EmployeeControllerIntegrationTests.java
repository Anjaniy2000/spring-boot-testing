package com.anjaniy.springboottesting.integration;

import com.anjaniy.springboottesting.models.Employee;
import com.anjaniy.springboottesting.repositories.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EmployeeControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Employee anjaniy;

    @BeforeEach
    void setup(){
        anjaniy = Employee.builder()
                .firstName("Anjaniy")
                .lastName("Salekar")
                .departmentName("JAVA")
                .designation("SE")
                .email("anjaniy01salekar@gmail.com")
                .contactNo("8799535110")
                .build();
        employeeRepository.deleteAll();
    }

    //JUnit test case to save employee - operation:
    @Test
    @DisplayName("JUnit Test Case To Save Employee - Operation")
    void givenEmployeeObject_whenSave_thenReturnSavedEmployeeObject() throws Exception{

        //given: - precondition or setup
//            Employee anjaniy = Employee.builder()
//                    .firstName("Anjaniy")
//                    .lastName("Salekar")
//                    .departmentName("JAVA")
//                    .designation("SE")
//                    .email("anjaniy01salekar@gmail.com")
//                    .contactNo("8799535110")
//                    .build();

//        BDDMockito.given(employeeService.saveEmployee(ArgumentMatchers.any(Employee.class))).willAnswer((invocation) -> invocation.getArgument(0));

        //when: - action or behavior that we are going to test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(anjaniy)));

        //then: - verify the output
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(anjaniy.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", CoreMatchers.is(anjaniy.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentName", CoreMatchers.is(anjaniy.getDepartmentName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.designation", CoreMatchers.is(anjaniy.getDesignation())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(anjaniy.getEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.contactNo", CoreMatchers.is(anjaniy.getContactNo())));
    }

    //JUnit test case to get all employees - operation:
    @Test
    @DisplayName("JUnit Test Case To Get All Employees - Operation")
    void givenEmptyEmployeeList_whenFindAll_thenReturnEmptyEmployeeList() throws Exception{

        //given: - precondition or setup
        Employee jay = Employee.builder()
                .firstName("Jay")
                .lastName("Shah")
                .departmentName("JAVA")
                .designation("SE")
                .email("jay101shah@gmail.com")
                .contactNo("7878969632")
                .build();

        List<Employee> employees = new ArrayList<>();
        employees.add(anjaniy);
        employees.add(jay);
        employeeRepository.saveAll(employees);

//        BDDMockito.given(employeeService.getAllEmployees()).willReturn(employees);

        //when: - action or behavior that we are going to test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/employee"));

        //then: - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(employees.size())));
    }

    //JUnit test case to get employee by id - operation:
    @Test
    @DisplayName("JUnit Test Case To Get Employee By ID - Operation")
    void givenEmployeeId_whenFindById_thenReturnEmployeeObject() throws Exception{
//        Long employeeId = 1L;

        //given: - precondition or setup
//            Employee anjaniy = Employee.builder()
//                    .firstName("Anjaniy")
//                    .lastName("Salekar")
//                    .departmentName("JAVA")
//                    .designation("SE")
//                    .email("anjaniy01salekar@gmail.com")
//                    .contactNo("8799535110")
//                    .build();

//        BDDMockito.given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(anjaniy));
        employeeRepository.save(anjaniy);

        //when: - action or behavior that we are going to test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/employee/{id}", anjaniy.getId()));

        //then: - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(anjaniy.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", CoreMatchers.is(anjaniy.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentName", CoreMatchers.is(anjaniy.getDepartmentName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.designation", CoreMatchers.is(anjaniy.getDesignation())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(anjaniy.getEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.contactNo", CoreMatchers.is(anjaniy.getContactNo())));
    }

    //JUnit test case to get employee by id - operation (Negative scenario):
    @Test
    @DisplayName("JUnit Test Case To Get Employee By ID - Operation [Negative Scenario]")
    void givenInvalidEmployeeId_whenFindById_thenReturnEmployeeObject() throws Exception{

        Long employeeId = 1L;

        //given: - precondition or setup
//            Employee anjaniy = Employee.builder()
//                    .firstName("Anjaniy")
//                    .lastName("Salekar")
//                    .departmentName("JAVA")
//                    .designation("SE")
//                    .email("anjaniy01salekar@gmail.com")
//                    .contactNo("8799535110")
//                    .build();

//        BDDMockito.given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.empty());
        employeeRepository.save(anjaniy);

        //when: - action or behavior that we are going to test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/employee/{id}", employeeId));

        //then: - verify the output
        response.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(print());
    }

    //JUnit test case to update employee - operation:
    @Test
    @DisplayName("JUnit Test Case To Update Employee - Operation")
    void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployeeObject() throws Exception{

        //given: - precondition or setup
//            Employee anjaniy = Employee.builder()
//                    .firstName("Anjaniy")
//                    .lastName("Salekar")
//                    .departmentName("JAVA")
//                    .designation("SE")
//                    .email("anjaniy01salekar@gmail.com")
//                    .contactNo("8799535110")
//                    .build();

//        Long employeeId = 1L;
        employeeRepository.save(anjaniy);

        Employee updatedEmployee = Employee.builder()
                .firstName("Anjaniy")
                .lastName("Salekar")
                .departmentName("DEVOPS")
                .designation("SSE")
                .email("anjaniy1010salekar@gmail.com")
                .contactNo("8799535110")
                .build();

//        BDDMockito.given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(anjaniy));
//        BDDMockito.given(employeeService.updateEmployee(ArgumentMatchers.any(Employee.class))).willAnswer((invocation) -> invocation.getArgument(0));

        //when: - action or behavior that we are going to test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/employee/{id}", anjaniy.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployee)));

        //then: - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(updatedEmployee.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", CoreMatchers.is(updatedEmployee.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentName", CoreMatchers.is(updatedEmployee.getDepartmentName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.designation", CoreMatchers.is(updatedEmployee.getDesignation())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(updatedEmployee.getEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.contactNo", CoreMatchers.is(updatedEmployee.getContactNo())));
    }

    //JUnit test case to update employee - operation (Negative Scenario):
    @Test
    @DisplayName("JUnit Test Case To Update Employee - Operation [Negative Scenario]")
    void givenEmployeeObject_whenUpdateEmployee_thenReturnStatusCode404() throws Exception {

        //given: - precondition or setup
//            Employee anjaniy = Employee.builder()
//                    .firstName("Anjaniy")
//                    .lastName("Salekar")
//                    .departmentName("JAVA")
//                    .designation("SE")
//                    .email("anjaniy01salekar@gmail.com")
//                    .contactNo("8799535110")
//                    .build();

        Long employeeId = 1L;
        employeeRepository.save(anjaniy);

        Employee updatedEmployee = Employee.builder()
                .firstName("Anjaniy")
                .lastName("Salekar")
                .departmentName("DEVOPS")
                .designation("SSE")
                .email("anjaniy1010salekar@gmail.com")
                .contactNo("8799535110")
                .build();

//        BDDMockito.given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.empty());
//        BDDMockito.given(employeeService.updateEmployee(ArgumentMatchers.any(Employee.class))).willAnswer((invocation) -> invocation.getArgument(0));

        //when: - action or behavior that we are going to test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/employee/{id}", employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployee)));

        //then: - verify the output
        response.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(print());
    }

    //JUnit test case to delete employee - operation:
    @Test
    @DisplayName("JUnit Test Case To Delete Employee - Operation")
    void givenEmployeeId_whenDelete_thenRemoveEmployee() throws Exception{

        //given: - precondition or setup
//            Employee anjaniy = Employee.builder()
//                    .firstName("Anjaniy")
//                    .lastName("Salekar")
//                    .departmentName("JAVA")
//                    .designation("SE")
//                    .email("anjaniy01salekar@gmail.com")
//                    .contactNo("8799535110")
//                    .build();
//        Long employeeId = 1L;
        employeeRepository.save(anjaniy);

//        BDDMockito.willDoNothing().given(employeeService).deleteEmployee(employeeId);

        //when: - action or behavior that we are going to test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete("/employee/{id}", anjaniy.getId()));

        //then: - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }
}
