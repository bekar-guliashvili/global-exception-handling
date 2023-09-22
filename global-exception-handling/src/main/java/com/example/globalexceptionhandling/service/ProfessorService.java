package com.example.globalexceptionhandling.service;

import com.example.globalexceptionhandling.entity.Professor;
import com.example.globalexceptionhandling.exception.AppException;
import com.example.globalexceptionhandling.exception.ErrorEnum;
import com.example.globalexceptionhandling.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repository;

    public Professor saveProfessor(Professor professor) throws AppException {
        if(repository.findProfessorByEmail(professor.getEmail()).isPresent()){
            throw new AppException(ErrorEnum.Professor_ALREADY_EXISTS);
        }
        return repository.save(professor);
    }

    public Professor getProfessorById(Long id) throws AppException {
        return repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorEnum.Professor_NOT_FOUND));
    }

    public List<Professor> getProfessors(){
        return repository.findAll();
    }


    public Professor updateProfessor(Professor professor) throws AppException {
        Professor professorToUpdate = repository.findById(professor.getId())
                .orElseThrow(() -> new AppException(ErrorEnum.Professor_NOT_FOUND));

        professorToUpdate.setName(professor.getName());
        professorToUpdate.setEmail(professor.getEmail());

        return repository.save(professorToUpdate);
    }


    public void deleteProfessor(Long id) throws AppException {
        Professor professorToDelete = repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorEnum.Professor_NOT_FOUND));
        repository.delete(professorToDelete);
    }
}
