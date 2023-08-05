package com.example.demo.student;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsStudentByName(String name);

    Student findStudentByName(String name);

    @Transactional
    void deleteStudentByName(String name);

}
