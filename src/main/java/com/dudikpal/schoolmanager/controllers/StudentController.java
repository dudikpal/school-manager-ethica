package com.dudikpal.schoolmanager.controllers;

import com.dudikpal.schoolmanager.commands.CreateStudentCommand;
import com.dudikpal.schoolmanager.commands.UpdateStudentCommand;
import com.dudikpal.schoolmanager.dto.StudentDTO;
import com.dudikpal.schoolmanager.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;


    @GetMapping
    public List<StudentDTO> getStudents(@RequestParam Optional<String> namePart,
                                        @RequestParam Optional<Integer> age) {
        return studentService.getStudents(namePart, age);
    }


    @PostMapping
    public StudentDTO createStudent(@RequestBody CreateStudentCommand command) {
        return studentService.createStudent(command);
    }


    @PutMapping("/{id}")
    public StudentDTO updateStudent(@PathVariable("id") long id, @RequestBody UpdateStudentCommand command) {
        return studentService.updateStudent(id, command);
    }


    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id")long id) {
        studentService.deleteStudent(id);
    }
}
