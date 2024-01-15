package com.example.demo.faculty;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }
    public Optional<Faculty> getFaculty(Long facultyId){
        return facultyRepository.findById(facultyId);
    }

    public void createFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long facultyId) {
        boolean isExist = facultyRepository.existsById(facultyId);
        if (!isExist){
            throw new IllegalStateException("Faculty with id " +facultyId + " not found");
        }
        facultyRepository.deleteById(facultyId);
    }
    @Transactional
    public void updateFaculty(Long facultyId, String facultyName, String deanName, Integer officeNumber) {
        Faculty faculty = facultyRepository.findById(facultyId).
                orElseThrow(()->new IllegalArgumentException("Faculty with id "+facultyId +" not found"));
        if (facultyName != null && !facultyName.isEmpty()){
            faculty.setFacultyName(facultyName);
        }
        if (deanName !=null && !deanName.isEmpty()){
            faculty.setDeanName(deanName);
        }
        if (officeNumber!=null){
            faculty.setOfficeNumber(officeNumber);
        }
    }
}
