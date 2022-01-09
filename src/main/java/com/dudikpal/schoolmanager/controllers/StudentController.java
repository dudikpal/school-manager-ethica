package com.dudikpal.schoolmanager.controllers;

import com.dudikpal.schoolmanager.commands.CreateStudentCommand;
import com.dudikpal.schoolmanager.commands.UpdateStudentCommand;
import com.dudikpal.schoolmanager.dtos.StudentDTO;
import com.dudikpal.schoolmanager.services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/students")
@Tag(name = "Operations on students")
public class StudentController {

    private StudentService studentService;


    @GetMapping()
    @Operation(summary = "Get all students, or filtering to fragment of name, and/or age")
    @ApiResponse(responseCode = "201", description = "student(s) finding success")
    public List<StudentDTO> getStudents(
            @RequestParam Optional<String> namePart,
            @RequestParam Optional<Integer> age
    ) {
        return studentService.getStudents(namePart, age);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get student by id")
    @ApiResponse(responseCode = "201", description = "student finding success")
    public StudentDTO getStudent(
            @Parameter(example = "1")
            @PathVariable("id")long id
    ) {
        return studentService.getStudent(id);
    }


    @PostMapping()
    @Operation(summary = "Create a student")
    @ApiResponse(responseCode = "201", description = "student has been created")
    public StudentDTO createStudent(@RequestBody CreateStudentCommand command) {

        return studentService.createStudent(command);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update a student")
    @ApiResponse(responseCode = "201", description = "student has been upgraded")
    public StudentDTO updateStudent(
            @Parameter(example = "1")
            @PathVariable("id") long id,
            @RequestBody UpdateStudentCommand command
    ) {
        return studentService.updateStudent(id, command);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete student")
    @ApiResponse(responseCode = "201", description = "student has been deleted")
    public void deleteStudent(
            @Parameter(example = "1")
            @PathVariable("id")long id
    ) {
        studentService.deleteStudent(id);
    }
}
