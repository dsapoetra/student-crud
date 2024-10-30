package com.example.thymeleaf.service;

import com.example.thymeleaf.entity.Student;
import com.example.thymeleaf.repository.AddressRepository;
import com.example.thymeleaf.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StudentService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private StudentRepository studentRepository;

    public Student findById(String id) {
        return this.studentRepository.findById(id).orElseThrow();
    }

    public Student save(Student student) {
        if (student.getId() == null) {  // Check if ID is not set
            student.setId(UUID.randomUUID().toString());  // Set a unique ID as String
        }
        Student savedStudent = studentRepository.save(student);
        System.out.println("Saved student: " + savedStudent); // Log the saved student
        return savedStudent;
    }

    public Student update(String id, Student student) {
        Student studentDatabase = this.findById(id);
        BeanUtils.copyProperties(student, studentDatabase, "id", "createdAt", "updatedAt", "address");
        BeanUtils.copyProperties(student.getAddress(), studentDatabase.getAddress(), "id", "createdAt", "updatedAt", "student");
        return this.studentRepository.save(studentDatabase);
    }

    public void deleteById(String id) {
        this.studentRepository.delete(this.findById(id));
    }

}
