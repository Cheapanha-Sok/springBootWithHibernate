package com.example.demo.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<?> getTeacher(@PathVariable("teacher_id") Long teacherId){
        Map<String , Object> map= new LinkedHashMap<String , Object>();
        Optional<Teacher> teacher = teacherService.getTeacher(teacherId);
        if (teacher.isPresent()){
            map.put("status" , 1);
            map.put("Teacher" , teacher);
            return new ResponseEntity<>(map , HttpStatus.OK);
        }else{
            map.put("status" , 0);
            map.put("message" , "Teacher with id" + teacherId + "not founded") ;
            return new ResponseEntity<>(map , HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<?> createTeacher(@RequestBody Teacher teacher){
        Map<String , Object> map= new LinkedHashMap<String , Object>();
        teacherService.createTeacher(teacher);
        map.put("status", 1);
        map.put("message", "Teacher is Saved Successfully");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
    @DeleteMapping(path = "{teacher_id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long teacher_id){
        Map<String , Object> map= new LinkedHashMap<String , Object>();
        boolean isDeleted = teacherService.deleteTeacher(teacher_id);
        if (isDeleted) {
            map.put("status", 1);
            map.put("message", "Record is deleted successfully!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Teacher with id " + teacher_id + " not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping(path = "{teacher_id}")
    public ResponseEntity<?> updateTeacher(
            @PathVariable ("teacher_id")Long teacherId,
            @RequestBody Teacher teacher){
        Map<String , Object> map= new LinkedHashMap<String , Object>();
        boolean isUpdated = teacherService.updateTeacher(teacherId , teacher);
        if (isUpdated) {
            map.put("status", 1);
            map.put("message", "Record is updated successfully!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Teacher with id " + teacherId + " not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }

    }
    @PutMapping(path = "{teacher_id}/{course_id}")
    public void assignCourse(@PathVariable ("teacher_id") Long teacherId ,
                             @PathVariable ("course_id") Long courseId){
        teacherService.assignCourse(teacherId,courseId);
    }
}
