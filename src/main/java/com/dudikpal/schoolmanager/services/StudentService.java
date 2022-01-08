package com.dudikpal.schoolmanager.services;

import com.dudikpal.schoolmanager.commands.CreateStudentCommand;
import com.dudikpal.schoolmanager.commands.UpdateStudentCommand;
import com.dudikpal.schoolmanager.dto.StudentDTO;
import com.dudikpal.schoolmanager.mapper.StudentMapper;
import com.dudikpal.schoolmanager.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.dudikpal.schoolmanager.entities.Student;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService {

    private StudentMapper studentMapper;

    private StudentRepository studentRepository;


    public List<StudentDTO> getStudents(Optional<String> namePart, Optional<Integer> age) {
        return studentRepository.findAll().stream()
                .filter(student -> (namePart.isEmpty() ||
                        student.getFirstName().toLowerCase().contains(namePart.get().toLowerCase()) ||
                        student.getLastName().toLowerCase().contains(namePart.get().toLowerCase()))
                && (age.isEmpty() || student.getAge() == age.get()))
                //.filter(student -> age.isEmpty() || student.getAge() == age.get())
                .map(student -> studentMapper.toDto(student))
                .collect(Collectors.toList());
    }

    public StudentDTO createStudent(CreateStudentCommand command) {
        Student student = new Student(
                command.getFirstName(),
                command.getLastName(),
                command.getAge()
        );

        studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    public StudentDTO updateStudent(long id, UpdateStudentCommand command) {
        Student student = studentRepository.getById(id);
        
    }
}
