package com.example.demo.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    @GetMapping
    public List<Teacher> getAllTeacher(){
        return teacherService.getAllTeacher();
    }
    @GetMapping(path = "{teacher_id}")
    public Optional<Teacher> getTeacher(@PathVariable("teacher_id") Long teacherId){
        return teacherService.getTeacher(teacherId);
    }
    @PostMapping
    public ResponseEntity<String> createTeacher(@RequestBody Teacher teacher){
        try{
            teacherService.createTeacher(teacher);
            return ResponseEntity.status(HttpStatus.CREATED).header("Created").body("Created Teacher Success");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Error").body("Failed to Created Teacher");
        }

    }
    @DeleteMapping(path = "{teacher_id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Long teacher_id){
        try{
            teacherService.deleteTeacher(teacher_id);
            return ResponseEntity.status(HttpStatus.OK).header("Deleted").body("Deleted Teacher Success");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Error").body("Failed to Delete Teacher");
        }

    }
    @PutMapping(path = "{teacher_id}")
    public void updateTeacher(
            @PathVariable ("teacher_id")Long teacherId,
            @RequestParam (required = false) String teacherName,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) LocalDate dob,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String phoneNumber
            ){
        teacherService.updateTeacher(teacherId,teacherName,gender,dob,address,phoneNumber);
    }
    @PutMapping(path = "{teacher_id}/{course_id}")
    public void assignCourse(@PathVariable ("teacher_id") Long teacherId ,
                             @PathVariable ("course_id") Long courseId){
        teacherService.assignCourse(teacherId,courseId);
    }
}
