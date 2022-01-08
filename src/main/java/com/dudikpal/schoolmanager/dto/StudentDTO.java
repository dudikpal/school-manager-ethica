package com.dudikpal.schoolmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private int id;

    private String firstName;

    private String lastName;

    private int age;
}
