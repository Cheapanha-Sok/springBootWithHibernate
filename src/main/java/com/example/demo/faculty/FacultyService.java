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
    @Transactional
    public void createFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
    }
    @Transactional
    public boolean deleteFaculty(Long facultyId) {
        boolean isExist = facultyRepository.existsById(facultyId);
        if (isExist){
            facultyRepository.deleteById(facultyId);
            return true;
        }
        return false;
    }
    @Transactional
    public boolean updateFaculty(Long facultyId, Faculty updateFaculty ) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(facultyId);
        if (optionalFaculty.isPresent()) {
            Faculty existingCourse = optionalFaculty.get();
            if (updateFaculty.getFacultyName() != null && !updateFaculty.getFacultyName().isEmpty()) {
                existingCourse.setFacultyName(updateFaculty.getFacultyName());
            }
            if (updateFaculty.getDeanName() != null && !updateFaculty.getDeanName().isEmpty()) {
                existingCourse.setDeanName(updateFaculty.getDeanName());
            }
            if (updateFaculty.getOfficeNumber() != null) {
                existingCourse.setOfficeNumber(updateFaculty.getOfficeNumber());
            }
            facultyRepository.save(existingCourse);
            return true;
        }
        return false;
    }
}
