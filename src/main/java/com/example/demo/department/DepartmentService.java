package com.example.demo.department;

import com.example.demo.faculty.Faculty;
import com.example.demo.faculty.FacultyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final FacultyRepository facultyRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, FacultyRepository facultyRepository) {
        this.departmentRepository = departmentRepository;
        this.facultyRepository = facultyRepository;
    }

    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }
    public Optional<Department> getDepartment(Long departmentId){
        return departmentRepository.findById(departmentId);
    }

    @Transactional
    public void addNewDepartment(Department department, Long facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new IllegalArgumentException("Faculty not found with ID: " + facultyId));
        // Set the faculty for the department using only the ID
        department.setFaculty(faculty);

        departmentRepository.save(department);
    }

    @Transactional
    public boolean deleteDepartment(Long departmentId) {
        boolean isExist = departmentRepository.existsById(departmentId);
        if (isExist) {
            departmentRepository.deleteById(departmentId);
            return true;
        }else{
            return false;
        }

    }

    @Transactional
    public String updateDepartment(Long departmentId, String departmentName, String headName, Integer officeNumber) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("Department with id " + departmentId + " not found"));

        if (departmentName != null && !departmentName.isEmpty()) {
            department.setDepartmentName(departmentName);
        }
        if (headName != null) {
            department.setHeadName(headName);
        }
        if (officeNumber != null) {
            department.setOfficeNumber(officeNumber);
        }

        departmentRepository.save(department);
        return "Department with id " + departmentId + " updated successfully";
    }
}
