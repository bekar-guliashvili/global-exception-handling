package com.example.globalexceptionhandling.controler;

import com.example.globalexceptionhandling.entity.Professor;
import com.example.globalexceptionhandling.exception.AppException;
import com.example.globalexceptionhandling.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService service;

    @PostMapping("/save")
    public ResponseEntity<Professor> addProfessor(@RequestBody @Valid Professor professor) throws AppException {
        return new ResponseEntity<>(service.saveProfessor(professor), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getProfessor(@PathVariable Long id) throws AppException {
        return ResponseEntity.ok(service.getProfessorById(id));
    }


    @GetMapping("/getAllProfessors")
    public ResponseEntity<List<Professor>> getProfessors(){
        return ResponseEntity.ok(service.getProfessors());
    }

    @PutMapping("/update")
    public ResponseEntity<Professor> updateProfessor(@RequestBody Professor professor) throws AppException {
        return ResponseEntity.ok(service.updateProfessor(professor));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProfessor(@PathVariable Long id) throws AppException {
        service.deleteProfessor(id);
        return ResponseEntity.ok("Professor by id " + id + " was deleted successfully!!!");
    }

}
