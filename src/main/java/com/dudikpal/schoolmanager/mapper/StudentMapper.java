package com.dudikpal.schoolmanager.mapper;

import com.dudikpal.schoolmanager.commands.CreateStudentCommand;
import com.dudikpal.schoolmanager.commands.UpdateStudentCommand;
import com.dudikpal.schoolmanager.entities.Student;
import com.dudikpal.schoolmanager.dto.StudentDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {


    Student fromDTO(CreateStudentCommand command, @MappingTarget Student student);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Student fromDTO(UpdateStudentCommand command, @MappingTarget Student student);

    StudentDTO toDTO(Student student);

}
