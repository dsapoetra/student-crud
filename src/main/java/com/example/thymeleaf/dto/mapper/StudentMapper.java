package com.example.thymeleaf.dto.mapper;

import com.example.thymeleaf.dto.CreateStudentDTO;
import com.example.thymeleaf.dto.UpdateStudentDTO;
import com.example.thymeleaf.dto.StudentResponseDTO;
import com.example.thymeleaf.entity.Address;
import com.example.thymeleaf.entity.Student;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentMapper {

    public static Student toEntity(CreateStudentDTO dto) {
        Student student = new Student();
        student.setId(UUID.randomUUID().toString());
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setBirthday(dto.getBirthday());
        student.setCreatedAt(LocalDateTime.now());

        Address address = new Address();
        address.setStreet(dto.getStreet());
        address.setNumber(dto.getNumber());
        address.setDistrict(dto.getDistrict());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        student.setAddress(address);

        return student;
    }

    // Overloaded method for UpdateStudentDTO
    public static Student toEntity(UpdateStudentDTO dto) {
        Student student = new Student();
        student.setId(dto.getId()); // Ensure that the ID is preserved
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());

        // Set other fields as required for updating
        // Address information could also be set similarly if provided in UpdateStudentDTO

        return student;
    }

    public static StudentResponseDTO toDTO(Student student) {
        if (student == null) return null;

        StudentResponseDTO dto = new StudentResponseDTO();
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setBirthday(student.getBirthday());
        dto.setStreet(student.getAddress() != null ? student.getAddress().getStreet() : null);
        dto.setDistrict(student.getAddress() != null ? student.getAddress().getDistrict() : null);
        dto.setCity(student.getAddress() != null ? student.getAddress().getCity() : null);
        dto.setState(student.getAddress() != null ? student.getAddress().getState() : null);
        dto.setCreatedAt(student.getCreatedAt());
        return dto;
    }

    public static List<StudentResponseDTO> toDTO(List<Student> students) {
        return students.stream()
                .map(StudentMapper::toDTO)
                .collect(Collectors.toList());
    }
}
