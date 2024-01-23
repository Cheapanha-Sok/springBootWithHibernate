package com.example.demo.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Student>> getAllStudent() {
        return ResponseEntity.ok(studentService.getAllStudent());
    }
    @GetMapping(path = "{student_id}")
    public ResponseEntity<?> getStudent(@PathVariable("student_id") Long studentId) {
        Optional<Student> student = studentService.getStudent(studentId);
        if (student.isPresent()){
            return ResponseEntity.ok(student.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("{department_id}")
    public ResponseEntity<?> registerNewStudent(@RequestBody Student student , @PathVariable("department_id")Long departmentId) {
        boolean isCreated = studentService.addNewStudent(student , departmentId);
        if (isCreated) {
            return ResponseEntity.created(URI.create("/api/v1/student/" + student.getStudentId())).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping(path = "{student_id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("student_id") Long studentId) {
        boolean isDeleted = studentService.deleteStudent(studentId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping(path = "{student_id}")
    public ResponseEntity<?> updateStudent(
            @PathVariable("student_id") Long studentId,
            @RequestBody Student student
    ) {
        boolean isUpdated = studentService.updateStudent(studentId , student);
        if (isUpdated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
