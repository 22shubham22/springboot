package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public ResponseEntity saveStudent(Student student) {
        if(this.studentRepository.existsStudentByName(student.getName())) {
            return new ResponseEntity("Already Exists", HttpStatus.CONFLICT);
        }
        try {
            this.studentRepository.save(student);
            return new ResponseEntity("Added", HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity("Failed",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity updateStudent(String name,Student student) {
        Student studentFromDb = this.studentRepository.findStudentByName(name);

        if (studentFromDb != null && Objects.equals(studentFromDb.getName(), student.getName())){
            studentFromDb.setName(student.getName());
            studentFromDb.setAge((student.getAge()));

            this.studentRepository.save(studentFromDb);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity deleteStudent(String name) {
        this.studentRepository.deleteStudentByName(name);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
