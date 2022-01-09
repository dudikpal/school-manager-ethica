package com.dudikpal.schoolmanager.commands;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentCommand {

    @NotBlank
    @Schema(example = "John")
    private String firstName;

    @NotBlank
    @Schema(example = "Doe")
    private String lastName;

    @Schema(example = "22")
    private int age;
}
