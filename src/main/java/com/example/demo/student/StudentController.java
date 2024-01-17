package com.example.demo.student;

import com.example.demo.department.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.Subject;
import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<?> getAllStudent() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<Student> students = studentService.getAllStudent();
        if (!students.isEmpty()) {
            map.put("status", 1);
            map.put("Student", students);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "No data found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(path = "{student_id}")
    public ResponseEntity<?> getStudent(@PathVariable("student_id") Long studentId) {
        Optional<Student> student = studentService.getStudent(studentId);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        if (student.isPresent()) {
            map.put("status", 1);
            map.put("Student", student);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Student with id" + studentId + "not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("{department_id}")
    public ResponseEntity<?> registerNewStudent(@RequestBody Student student , @PathVariable("department_id")Long departmentId) {
        boolean isCreated = studentService.addNewStudent(student , departmentId);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        if (isCreated) {
            map.put("status", 1);
            map.put("message", "Student is Saved Successfully");
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        } else {
            map.put("status", 0);
            map.put("message", "Department with id " + departmentId + " not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "{student_id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("student_id") Long studentId) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        boolean isDeleted = studentService.deleteStudent(studentId);
        if (isDeleted) {
            map.put("status", 1);
            map.put("message", "Record is deleted successfully!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Student with id " + studentId + " not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "{student_id}")
    public ResponseEntity<?> updateStudent(
            @PathVariable("student_id") Long studentId,
            @RequestBody Student student
    ) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        boolean isUpdated = studentService.updateStudent(studentId , student);
        if (isUpdated) {
            map.put("status", 1);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Student with id " + studentId + " not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }
}
