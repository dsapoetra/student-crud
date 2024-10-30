package com.example.thymeleaf.controller;

import com.example.thymeleaf.dto.CreateStudentDTO;
import com.example.thymeleaf.dto.StudentResponseDTO;
import com.example.thymeleaf.dto.UpdateStudentDTO;
import com.example.thymeleaf.dto.mapper.StudentMapper;
import com.example.thymeleaf.repository.StudentRepository;
import com.example.thymeleaf.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;

    // Display all students
    @GetMapping
    public ModelAndView showStudents() {
        List<StudentResponseDTO> students = StudentMapper.toDTO(this.studentRepository.findAll());
        System.out.println("Fetched students: " + students.toString()); // Debugging line
        return new ModelAndView("students").addObject("students", students);
    }

    // Show form to create a new student
    @GetMapping("/new")
    public ModelAndView showCreateForm() {
        return new ModelAndView("new-student").addObject("student", new CreateStudentDTO());
    }

    // Process form submission for creating a new student
    @PostMapping("/new")
    public String createStudent(@ModelAttribute("student") @Valid CreateStudentDTO studentDTO,
                                BindingResult result, RedirectAttributes attributes) {
        System.out.println("Raw Request Data: " + studentDTO);  // Log the studentDTO for debugging

        if (result.hasErrors()) {
            return "new-student";
        }

        studentService.save(StudentMapper.toEntity(studentDTO));
        attributes.addFlashAttribute("message", "Student created successfully!");
        return "redirect:/students";
    }


    // Show form to update an existing student
    @GetMapping("/{id}")
    public ModelAndView showUpdateForm(@PathVariable String id) {
        StudentResponseDTO responseDTO = StudentMapper.toDTO(this.studentService.findById(id));
        return new ModelAndView("edit-student").addObject("student", responseDTO);
    }

    // Process form submission for updating an existing student
    @PostMapping("/{id}")
    public String update(@PathVariable String id, @ModelAttribute("student") @Valid UpdateStudentDTO studentDTO,
                         BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "edit-student";
        }

        studentService.update(id, StudentMapper.toEntity(studentDTO));
        attributes.addFlashAttribute("message", "Student updated successfully!");
        return "redirect:/students";
    }

    // Delete a student
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes attributes) {
        studentService.deleteById(id);
        attributes.addFlashAttribute("message", "Student deleted successfully!");
        return "redirect:/students";
    }
}
