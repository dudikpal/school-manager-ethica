package com.dudikpal.schoolmanager.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentCommand {

    private String firstName;

    private String lastName;

    private int age;
}
