package com.example.demo.service;

import com.example.demo.model.Department;
import com.example.demo.model.Student;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.response.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentService {
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    public StudentService(StudentRepository studentRepository, DepartmentRepository departmentRepository) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }

    public ResponseEntity<Iterable<Student>> getAllStudent() {
        return ResponseEntity.ok(studentRepository.findAll());
    }

    public ResponseEntity<Optional<Student> >getStudent(Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()){
            return ResponseEntity.ok(student);
        }
        throw new NotFoundException("Student with id=" + studentId + " not found");
    }

    public ResponseEntity<URI> addNewStudent(Student student, Long departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isPresent()){
            student.setDepartments(List.of(department.get()));
            studentRepository.save(student);
            return ResponseEntity.created(URI.create("/api/v1/student/" + student.getStudentId())).build();
        }
        throw new NotFoundException("Department with id=" + departmentId + " not found");
    }

    public ResponseEntity<HttpStatus> deleteStudent(Long studentId) {
        boolean isExist = studentRepository.existsById(studentId);
        if (isExist) {
            studentRepository.deleteById(studentId);
            return ResponseEntity.noContent().build();
        }
        throw new NotFoundException("Student with id=" + studentId + " not found");
    }

    public ResponseEntity<Student> updateStudent(Long studentId, Student updateStudent) {
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
            return ResponseEntity.ok(existingStudent);
        }
        throw new NotFoundException("Student with id=" + studentId + " not found");
    }

}
