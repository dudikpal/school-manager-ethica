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

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService {

    private ModelMapper modelMapper;

    private StudentMapper studentMapper;

    private StudentRepository studentRepository;


    public List<StudentDTO> getStudents(Optional<String> namePart, Optional<Integer> age) {
        return studentRepository.findAll().stream()
                .filter(student -> (namePart.isEmpty() ||
                        student.getFirstName().toLowerCase().contains(namePart.get().toLowerCase()) ||
                        student.getLastName().toLowerCase().contains(namePart.get().toLowerCase()))
                        && (age.isEmpty() || student.getAge() == age.get()))
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }


    private Student getStudent(long id) {
        return studentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cannot find student with this id:" + id));
    }


    public StudentDTO createStudent(CreateStudentCommand command) {
        Student student = new Student();
        studentMapper.fromDTO(command, student);
        studentRepository.save(student);

        return modelMapper.map(student, StudentDTO.class);
    }


    @Transactional
    public StudentDTO updateStudent(long id, UpdateStudentCommand command) {
        Student student = getStudent(id);
        studentMapper.fromDTO(command, student);

        return studentMapper.toDTO(student);
    }

    public void deleteStudent(long id) {
        studentRepository.delete(getStudent(id));
    }
}
