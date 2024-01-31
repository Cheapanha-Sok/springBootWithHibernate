package com.example.demo.department;

import com.example.demo.faculty.Faculty;
import com.example.demo.faculty.FacultyRepository;
import com.example.demo.response.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

@Service
@Transactional
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final FacultyRepository facultyRepository;
    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, FacultyRepository facultyRepository) {
        this.departmentRepository = departmentRepository;
        this.facultyRepository = facultyRepository;
    }

    public ResponseEntity<Iterable<Department>> getAllDepartment() {
       return ResponseEntity.ok(departmentRepository.findAll());
    }
    public ResponseEntity<Department> getDepartment(Long departmentId){
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isPresent()){
            return ResponseEntity.ok(department.get());
        }
        throw new NotFoundException("Department with id=" + departmentId + " not found");
    }

    public ResponseEntity<URI> addNewDepartment(Department department, Long facultyId) {
        Optional<Faculty> faculty = facultyRepository.findById(facultyId);
        if (faculty.isPresent()){
            department.setFaculty(faculty.get());
            departmentRepository.save(department);
            return ResponseEntity.created(URI.create("/api/v1/department/" + department.getDepartmentId())).build();
        }
        throw new NotFoundException("Faculty with id=" + facultyId + " not found");
    }

    public ResponseEntity<HttpStatus> deleteDepartment(Long departmentId) {
        boolean isExist = departmentRepository.existsById(departmentId);
        if (isExist) {
            departmentRepository.deleteById(departmentId);
            return ResponseEntity.noContent().build();
        }
        throw new NotFoundException("Department with id=" + departmentId + " not found");
    }

    public ResponseEntity<Department> updateDepartment(Long departmentId, Department updatedepartment) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isPresent()) {
            Department existingDepartment = department.get();
            if (updatedepartment.getDepartmentName() != null && !updatedepartment.getDepartmentName().isEmpty()) {
                existingDepartment.setDepartmentName(updatedepartment.getDepartmentName());
            }
            if (updatedepartment.getHeadName() != null && !updatedepartment.getHeadName().isEmpty()) {
                existingDepartment.setHeadName(updatedepartment.getHeadName());
            }
            if (updatedepartment.getOfficeNumber() != null) {
                existingDepartment.setOfficeNumber(updatedepartment.getOfficeNumber());
            }
            departmentRepository.save(existingDepartment);
            return ResponseEntity.ok(existingDepartment);
        }
        throw new NotFoundException("Department with id=" + departmentId + " not found");
    }
}
