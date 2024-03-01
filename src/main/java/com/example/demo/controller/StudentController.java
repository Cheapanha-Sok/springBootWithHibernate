package com.example.demo.controller;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/student/")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Student>> getAllStudent() {
        return studentService.getAllStudent();
    }
    @GetMapping(path = "{student_id}")
    public ResponseEntity<Optional<Student>> getStudent(@PathVariable("student_id") Long studentId) {
        return studentService.getStudent(studentId);
    }
    @PostMapping("{department_id}")
    public ResponseEntity<URI> registerNewStudent(@RequestBody Student student , @PathVariable("department_id")Long departmentId) {
        return studentService.addNewStudent(student , departmentId);
    }
    @DeleteMapping(path = "{student_id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("student_id") Long studentId) {
        return studentService.deleteStudent(studentId);
    }
    @PutMapping(path = "{student_id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable("student_id") Long studentId,
            @RequestBody Student student
    ) {
        return studentService.updateStudent(studentId , student);
    }
}
