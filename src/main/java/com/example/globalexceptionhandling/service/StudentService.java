package com.example.globalexceptionhandling.service;

import com.example.globalexceptionhandling.entity.Student;
import com.example.globalexceptionhandling.exception.AppException;
import com.example.globalexceptionhandling.exception.ErrorEnum;
import com.example.globalexceptionhandling.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public Student saveStudent(Student professor) throws AppException {
        if(repository.findStudentByEmail(professor.getEmail()).isPresent()){
            throw new AppException(ErrorEnum.Professor_ALREADY_EXISTS);
        }
        return repository.save(professor);
    }

    public Student getStudentById(Long id) throws AppException {
        return repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorEnum.Student_NOT_FOUND));
    }

    public List<Student> getStudents(){
        return repository.findAll();
    }

    public Student updateStudent(Student student) throws AppException {
        Student studentToUpdate = repository.findById(student.getId())
                .orElseThrow(() -> new AppException(ErrorEnum.Student_NOT_FOUND));

        studentToUpdate.setName(student.getName());
        studentToUpdate.setEmail(student.getEmail());

        return repository.save(studentToUpdate);
    }

    public void deleteStudent(Long id) throws AppException {
        Student studentToDelete = repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorEnum.Student_NOT_FOUND));
        repository.delete(studentToDelete);
    }




}
