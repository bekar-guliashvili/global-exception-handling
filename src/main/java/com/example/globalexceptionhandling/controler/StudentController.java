package com.example.globalexceptionhandling.controler;

import com.example.globalexceptionhandling.entity.Student;
import com.example.globalexceptionhandling.exception.AppException;
import com.example.globalexceptionhandling.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping("/save")
    public ResponseEntity<Student> addStudent(@RequestBody @Valid Student student) throws AppException {
        return new ResponseEntity(service.saveStudent(student), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) throws AppException {
        return ResponseEntity.ok(service.getStudentById(id));
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<List<Student>> getStudents(){
        return ResponseEntity.ok(service.getStudents());
    }

    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) throws AppException {
        return ResponseEntity.ok(service.updateStudent(student));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) throws AppException {
        service.deleteStudent(id);
        return ResponseEntity.ok("Student by id "+ id +" was deleted successfully!!!");
    }


}
