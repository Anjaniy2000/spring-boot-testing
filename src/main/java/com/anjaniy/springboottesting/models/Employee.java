package com.anjaniy.springboottesting.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First Name Is Required!")
    private String firstName;

    @NotBlank(message = "Last Name Is Required!")
    private String lastName;

    @NotBlank(message = "Department Name Is Required!")
    private String departmentName;

    @NotBlank(message = "Designation Is Required!")
    private String designation;

    @Email(message = "Please Enter A Valid Email Address!")
    @NotBlank(message = "Email Address Is Required!")
    private String email;

    @NotBlank(message = "Contact Number Is Required!")
    private String contactNo;
}
