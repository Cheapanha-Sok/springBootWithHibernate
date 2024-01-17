package com.example.demo.student;

import com.example.demo.course.Course;
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

    public Optional<Student> getStudent(Long studentId) {
        return studentRepository.findById(studentId);
    }

    public boolean addNewStudent(Student student, Long departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isPresent()){
            student.setDepartments(List.of(department.get()));
            studentRepository.save(student);
            return true;
        }
        return false;
    }

    public boolean deleteStudent(Long studentId) {
        boolean isExist = studentRepository.existsById(studentId);
        if (isExist) {
            studentRepository.deleteById(studentId);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean updateStudent(Long studentId, Student updateStudent) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (studentOptional.isPresent()) {
            Student existingStudent = studentOptional.get();

            if (updateStudent.getStudentName() != null && !updateStudent.getStudentName().isEmpty()) {
                existingStudent.setStudentName(updateStudent.getStudentName());
            }

            if (updateStudent.getGender() != null && !updateStudent.getGender().isEmpty()) {
                existingStudent.setGender(updateStudent.getGender());
            }

            if (updateStudent.getDob() != null) {
                existingStudent.setDob(updateStudent.getDob());
            }

            if (updateStudent.getPhoneNumber() != null && !updateStudent.getPhoneNumber().isEmpty()) {
                existingStudent.setPhoneNumber(updateStudent.getPhoneNumber());
            }

            if (updateStudent.getAddress() != null && !updateStudent.getAddress().isEmpty()) {
                existingStudent.setAddress(updateStudent.getAddress());
            }

            if (updateStudent.getStudentYear() != null) {
                existingStudent.setStudentYear(updateStudent.getStudentYear());
            }

            if (updateStudent.getDegree() != null && !updateStudent.getDegree().isEmpty()) {
                existingStudent.setDegree(updateStudent.getDegree());
            }
            if (updateStudent.getGeneration() != null) {
                existingStudent.setGeneration(updateStudent.getGeneration());
            }

            studentRepository.save(existingStudent);
            return true;
        }
        return false;
    }

}
