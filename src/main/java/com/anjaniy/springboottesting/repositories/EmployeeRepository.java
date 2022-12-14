package com.anjaniy.springboottesting.repositories;

import com.anjaniy.springboottesting.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail (String email);

    //define custom query using JPQL with index parameters:
    @Query("select employee from Employee employee where employee.firstName = ?1 and employee.lastName = ?2")
    Employee findByJPQL(String firstName, String lastName);

    //define custom query using JPQL with named parameters:
    @Query("select employee from Employee employee where employee.firstName = :firstName and employee.lastName = :lastName")
    Employee findByJPQLWithNamedParams(@Param("firstName") String firstName, @Param("lastName") String lastName);

    //define custom query using Native SQL with index parameters:
    @Query(value = "select * from employee employee where employee.first_name = ?1 and employee.last_name = ?2", nativeQuery = true)
    Employee findByNativeSQL(String firstName, String lastName);

    //define custom query using Native SQL with named parameters:
    @Query(value = "select * from employee employee where employee.first_name = :firstName and employee.last_name = :lastName", nativeQuery = true)
    Employee findByNativeSQLWithNamedParams(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
