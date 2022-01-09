package com.dudikpal.schoolmanager.repositories;

import com.dudikpal.schoolmanager.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepository extends JpaRepository<Student, Long> {
}
