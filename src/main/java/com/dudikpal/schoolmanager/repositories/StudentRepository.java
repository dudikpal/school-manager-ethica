package com.dudikpal.schoolmanager.repositories;

import com.dudikpal.schoolmanager.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
