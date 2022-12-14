package com.anjaniy.springboottesting.repositories;

import com.anjaniy.springboottesting.models.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee anjaniy;
    private Employee jay;

    @BeforeEach
    public void setup(){
        anjaniy = Employee.builder()
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
//            Employee anjaniy = Employee.builder()
//                    .firstName("Anjaniy")
//                    .lastName("Salekar")
//                    .departmentName("JAVA")
//                    .designation("SE")
//                    .email("anjaniy01salekar@gmail.com")
//                    .contactNo("8799535110")
//                    .build();

            //when: - action or behavior that we are going to test
            Employee savedEmployee = employeeRepository.save(anjaniy);

            //then: - verify the output
            Assertions.assertThat(savedEmployee).isNotNull();
            Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);
        }

        //JUnit test case to get all employees - operation:
        @Test
        @DisplayName("JUnit Test Case To Get All Employees - Operation")
        void givenEmployeeList_whenFindAll_thenReturnEmployeeList(){

            //given: - precondition or setup
//            Employee anjaniy = Employee.builder()
//                    .firstName("Anjaniy")
//                    .lastName("Salekar")
//                    .departmentName("JAVA")
//                    .designation("SE")
//                    .email("anjaniy01salekar@gmail.com")
//                    .contactNo("8799535110")
//                    .build();
//
            Employee jay = Employee.builder()
                    .firstName("Jay")
                    .lastName("Shah")
                    .departmentName("JAVA")
                    .designation("SE")
                    .email("jay101shah@gmail.com")
                    .contactNo("7878969632")
                    .build();

            Employee savedEmployeeAnjaniy = employeeRepository.save(anjaniy);
            Employee savedEmployeeJay = employeeRepository.save(jay);

            //when: - action or behavior that we are going to test
            List<Employee> employeeList = employeeRepository.findAll();

            //then: - verify the output
            Assertions.assertThat(employeeList).isNotNull();
            Assertions.assertThat(employeeList.size()).isEqualTo(2);
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

            employeeRepository.save(anjaniy);

            //when: - action or behavior that we are going to test
            Employee savedEmployeeAnjaniyInDB = employeeRepository.findById(anjaniy.getId()).get();

            //then: - verify the output
            Assertions.assertThat(savedEmployeeAnjaniyInDB).isNotNull();
        }

        //JUnit test case to get employee by email - operation:
        @Test
        @DisplayName("JUnit Test Case To Get Employee By Email - Operation")
        void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject(){

            //given: - precondition or setup
//            Employee anjaniy = Employee.builder()
//                    .firstName("Anjaniy")
//                    .lastName("Salekar")
//                    .departmentName("JAVA")
//                    .designation("SE")
//                    .email("anjaniy01salekar@gmail.com")
//                    .contactNo("8799535110")
//                    .build();

            employeeRepository.save(anjaniy);

            //when: - action or behavior that we are going to test
            Employee savedEmployeeAnjaniyInDB = employeeRepository.findByEmail(anjaniy.getEmail()).get();

            //then: - verify the output
            Assertions.assertThat(savedEmployeeAnjaniyInDB).isNotNull();
            Assertions.assertThat(savedEmployeeAnjaniyInDB.getEmail()).isEqualTo(anjaniy.getEmail());
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

            employeeRepository.save(anjaniy);

            //when: - action or behavior that we are going to test
            Employee savedEmployeeAnjaniy = employeeRepository.findById(anjaniy.getId()).get();
            savedEmployeeAnjaniy.setDesignation("SSE");
            savedEmployeeAnjaniy.setDepartmentName("PRODUCT DESIGN");
            Employee updatedEmployeeAnjaniy = employeeRepository.save(savedEmployeeAnjaniy);

            //then: - verify the output
            Assertions.assertThat(updatedEmployeeAnjaniy).isNotNull();
            Assertions.assertThat(updatedEmployeeAnjaniy.getDesignation()).isEqualTo("SSE");
            Assertions.assertThat(updatedEmployeeAnjaniy.getDepartmentName()).isEqualTo("PRODUCT DESIGN");
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

            employeeRepository.save(anjaniy);

            //when: - action or behavior that we are going to test
            employeeRepository.deleteById(anjaniy.getId());
            Optional<Employee> employeeOptional = employeeRepository.findById(anjaniy.getId());

            //then: - verify the output
            Assertions.assertThat(employeeOptional).isEmpty();
        }

        //JUnit test case to get employee by firstName + lastName - operation (custom query using JPQL with index parameters):
        @Test
        @DisplayName("JUnit Test Case To Get Employee By FirstName & LastName - Operation (JPQL[Index Params])")
        void givenFirstNameAndLastName_whenFindByJPQL_thenEmployeeObject(){

            //given: - precondition or setup
//            Employee anjaniy = Employee.builder()
//                    .firstName("Anjaniy")
//                    .lastName("Salekar")
//                    .departmentName("JAVA")
//                    .designation("SE")
//                    .email("anjaniy01salekar@gmail.com")
//                    .contactNo("8799535110")
//                    .build();

            employeeRepository.save(anjaniy);

            //when: - action or behavior that we are going to test
            Employee savedEmployeeAnjaniy = employeeRepository.findByJPQL(anjaniy.getFirstName(), anjaniy.getLastName());

            //then: - verify the output
            Assertions.assertThat(savedEmployeeAnjaniy).isNotNull();
        }

        //JUnit test case to get employee by firstName + lastName - operation (custom query using JPQL with named parameters):
        @Test
        @DisplayName("JUnit Test Case To Get Employee By FirstName & LastName - Operation (JPQL[Named Params])")
        void givenFirstNameAndLastName_whenFindByJPQLWithNamedParams_thenEmployeeObject(){

            //given: - precondition or setup
//            Employee anjaniy = Employee.builder()
//                    .firstName("Anjaniy")
//                    .lastName("Salekar")
//                    .departmentName("JAVA")
//                    .designation("SE")
//                    .email("anjaniy01salekar@gmail.com")
//                    .contactNo("8799535110")
//                    .build();

            employeeRepository.save(anjaniy);

            //when: - action or behavior that we are going to test
            Employee savedEmployeeAnjaniy = employeeRepository.findByJPQLWithNamedParams(anjaniy.getFirstName(), anjaniy.getLastName());

            //then: - verify the output
            Assertions.assertThat(savedEmployeeAnjaniy).isNotNull();
        }

        //JUnit test case to get employee by firstName + lastName - operation (custom query using Native SQL with index parameters):
        @Test
        @DisplayName("JUnit Test Case To Get Employee By FirstName & LastName - Operation (Native SQL[Index Params])")
        void givenFirstNameAndLastName_whenFindByNativeSQL_thenEmployeeObject(){

            //given: - precondition or setup
//            Employee anjaniy = Employee.builder()
//                    .firstName("Anjaniy")
//                    .lastName("Salekar")
//                    .departmentName("JAVA")
//                    .designation("SE")
//                    .email("anjaniy01salekar@gmail.com")
//                    .contactNo("8799535110")
//                    .build();

            employeeRepository.save(anjaniy);

            //when: - action or behavior that we are going to test
            Employee savedEmployeeAnjaniy = employeeRepository.findByNativeSQL(anjaniy.getFirstName(), anjaniy.getLastName());

            //then: - verify the output
            Assertions.assertThat(savedEmployeeAnjaniy).isNotNull();
        }

        //JUnit test case to get employee by firstName + lastName - operation (custom query using Native SQL with named parameters):
        @Test
        @DisplayName("JUnit Test Case To Get Employee By FirstName & LastName - Operation (Native SQL[Named Params])")
        void givenFirstNameAndLastName_whenFindByNativeSQLWithNamedParams_thenEmployeeObject(){

            //given: - precondition or setup
//            Employee anjaniy = Employee.builder()
//                    .firstName("Anjaniy")
//                    .lastName("Salekar")
//                    .departmentName("JAVA")
//                    .designation("SE")
//                    .email("anjaniy01salekar@gmail.com")
//                    .contactNo("8799535110")
//                    .build();

            employeeRepository.save(anjaniy);

            //when: - action or behavior that we are going to test
            Employee savedEmployeeAnjaniy = employeeRepository.findByNativeSQLWithNamedParams(anjaniy.getFirstName(), anjaniy.getLastName());

            //then: - verify the output
            Assertions.assertThat(savedEmployeeAnjaniy).isNotNull();
        }
}
