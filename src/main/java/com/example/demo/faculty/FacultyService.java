package com.example.demo.faculty;

import com.example.demo.response.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public ResponseEntity<Iterable<Faculty>> getAllFaculty() {
       return ResponseEntity.ok(facultyRepository.findAll());
    }
    public ResponseEntity<Optional<Faculty>> getFaculty(Long facultyId){
        Optional<Faculty> faculty = facultyRepository.findById(facultyId);
        if (faculty.isPresent()){
            return ResponseEntity.ok(faculty);
        }
        throw new NotFoundException("Faculty with id=" + facultyId + "not found");
    }

    public ResponseEntity<URI> createFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
        return ResponseEntity.created(URI.create("/api/v1/faculty/" + faculty.getFacultyId())).build();
    }

    public ResponseEntity<HttpStatus> deleteFaculty(Long facultyId) {
        boolean isExist = facultyRepository.existsById(facultyId);
        if (isExist){
            facultyRepository.deleteById(facultyId);
            return ResponseEntity.noContent().build();
        }
        throw new NotFoundException("Faculty with id=" + facultyId + "not found");
    }

    public ResponseEntity<HttpStatus> updateFaculty(Long facultyId, Faculty updateFaculty ) {
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
            return ResponseEntity.ok().build();
        }
        throw new NotFoundException("Faculty with id=" + facultyId + "not found");
    }
}
