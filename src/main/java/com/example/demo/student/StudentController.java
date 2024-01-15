package com.example.demo.student;

import com.example.demo.department.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.Subject;
import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
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
    public List<Student> getAllStudent() {
        return studentService.getAllStudent();
    }
    @GetMapping(path = "{student_id}")
    public Optional<Student>getStudent(@PathVariable("student_id") Long studentId){
        return studentService.getStudent(studentId);
    }
    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }
    @DeleteMapping(path ="{student_id}")
    public void deleteStudent(@PathVariable("student_id")Long studentId){
        studentService.deleteStudent(studentId);
    }
    @PutMapping(path = "{student_id}")
    public void updateStudent(
            @PathVariable("student_id") Long studentId,
            @RequestParam(required = false) String studentName,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) LocalDate dob,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Integer generation,
            @RequestParam(required = false) Integer studentYear,
            @RequestParam(required = false) String degree
    ){
        studentService.updateStudent(studentId,studentName,gender,dob,phoneNumber,address,generation,studentYear,degree);
    }
    @PutMapping("{student_id}/{department_id}")
    public void enrolledStudent(
        @PathVariable ("student_id") Long studentId,
        @PathVariable ("department_id") Long departmentId

    ){
        studentService.enrolledStudent(studentId,departmentId);
    }
}
