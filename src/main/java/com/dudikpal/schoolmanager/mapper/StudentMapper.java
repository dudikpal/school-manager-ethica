package com.dudikpal.schoolmanager.mapper;

import com.dudikpal.schoolmanager.entities.Student;
import com.dudikpal.schoolmanager.dto.StudentDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDTO toDto(Student student);

    List<StudentDTO> toDto(List<StudentDTO> students);
}
