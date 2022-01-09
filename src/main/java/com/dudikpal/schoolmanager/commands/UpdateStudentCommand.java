package com.dudikpal.schoolmanager.commands;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentCommand {

    @Schema(example = "Jack")
    private String firstName;

    @Schema(example = "Mira")
    private String lastName;

    @Schema(example = "33")
    private Integer age;
}
