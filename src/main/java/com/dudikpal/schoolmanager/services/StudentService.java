package com.dudikpal.schoolmanager.services;

import com.dudikpal.schoolmanager.commands.CreateStudentCommand;
import com.dudikpal.schoolmanager.commands.UpdateStudentCommand;
import com.dudikpal.schoolmanager.dtos.StudentDTO;
import com.dudikpal.schoolmanager.mapper.IStudentMapper;
import com.dudikpal.schoolmanager.repositories.IStudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.dudikpal.schoolmanager.entities.Student;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService {

    private IStudentMapper studentMapper;

    private IStudentRepository IStudentRepository;


    public List<StudentDTO> getStudents(
            Optional<String> namePart,
            Optional<Integer> age
    ) {
        return IStudentRepository.findAll().stream()
                .filter(student -> (namePart.isEmpty() ||
                        student.getFirstName().toLowerCase().contains(namePart.get().toLowerCase()) ||
                        student.getLastName().toLowerCase().contains(namePart.get().toLowerCase()))
                        && (age.isEmpty() || student.getAge() == age.get()))
                .map(student -> studentMapper.toDTO(student))
                .collect(Collectors.toList());
    }


    private Student getStudentById(long id) {

        return IStudentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cannot find student with this id:" + id));
    }


    public StudentDTO getStudent(long id) {

        return studentMapper.toDTO(getStudentById(id));
    }


    public StudentDTO createStudent(CreateStudentCommand command) {
        Student student = new Student();
        studentMapper.fromDTO(command, student);
        IStudentRepository.save(student);

        return studentMapper.toDTO(student);
    }


    @Transactional
    public StudentDTO updateStudent(long id, UpdateStudentCommand command) {
        Student student = getStudentById(id);
        studentMapper.fromDTO(command, student);

        return studentMapper.toDTO(student);
    }


    public void deleteStudent(long id) {
        IStudentRepository.delete(getStudentById(id));
    }
}
