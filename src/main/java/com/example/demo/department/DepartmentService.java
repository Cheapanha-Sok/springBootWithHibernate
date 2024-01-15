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
    public boolean addNewDepartment(Department department, Long facultyId) {
        Optional<Faculty> faculty = facultyRepository.findById(facultyId);
        if (faculty.isPresent()){
            department.setFaculty(faculty.get());
            departmentRepository.save(department);
            return true;
        }
        return false;
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
    public boolean updateDepartment(Long departmentId, Department updatedepartment) {
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
            return true;
        }
        return false;
    }
}
