package com.example.demo.student;

import com.example.demo.department.Department;
import com.example.demo.department.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, DepartmentRepository departmentRepository) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }
    public Optional<Student> getStudent(Long studentId){
        return studentRepository.findById(studentId);
    }
    public void addNewStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean isExist = studentRepository.existsById(studentId);
        if (!isExist){
            throw new IllegalStateException("Student with id " + studentId + " not found" );
        }
        studentRepository.deleteById(studentId);

    }
    @Transactional
    public void updateStudent(Long studentId,String studentName,String gender,LocalDate dob,String phoneNumber,String address,Integer generation,Integer studentYear,String degree) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with id " + studentId + " not found"));

        // Update individual attributes if they are not null or empty
        if (studentName != null && !studentName.isEmpty()) {
            student.setStudentName(studentName);
        }

        if (gender!= null && !gender.isEmpty()) {
            student.setGender(gender);
        }

        if (dob != null) {
            student.setDob(dob);
        }

        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            student.setPhoneNumber(phoneNumber);
        }

        if (address != null && address.isEmpty()) {
            student.setAddress(address);
        }

        if (generation != null) {
            student.setGeneration(generation);
        }

        if (studentYear != null) {
            student.setStudentYear(studentYear);
        }

        if (degree!= null && !degree.isEmpty()) {
            student.setDegree(degree);
        }
        studentRepository.save(student);
    }
    @Transactional
    public void enrolledStudent(Long studentId, Long departmentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with id " + studentId + " not found"));

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalStateException("Department with id " + departmentId + " not found"));

        // Check if the student is already enrolled in the department
        if (student.getDepartments().contains(department)) {
            throw new IllegalStateException("Student with id " + studentId + " is already enrolled in Department with id " + departmentId);
        }

        student.setDepartment(department);
        studentRepository.save(student);
    }

}
