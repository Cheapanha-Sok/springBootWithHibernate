package com.example.demo.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public ResponseEntity<Iterable<Teacher>>getAllTeacher(){
        return ResponseEntity.ok(teacherService.getAllTeacher());
    }
    @GetMapping(path = "{teacher_id}")
    public ResponseEntity<?> getTeacher(@PathVariable("teacher_id") Long teacherId){
        Optional<Teacher> teacher = teacherService.getTeacher(teacherId);
        if (teacher.isPresent()){
            return ResponseEntity.ok(teacher.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("{course_id}")
    public ResponseEntity<?> createTeacher(@RequestBody Teacher teacher , @PathVariable("course_id") Long courseId){
        boolean isCreated = teacherService.createTeacher(teacher , courseId);
        if (isCreated) {
            return ResponseEntity.created(URI.create("/api/v1/teacher/" + teacher.getTeacherId())).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping(path = "{teacher_id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long teacher_id){
        boolean isDeleted = teacherService.deleteTeacher(teacher_id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping(path = "{teacher_id}")
    public ResponseEntity<?> updateTeacher(
            @PathVariable ("teacher_id")Long teacherId,
            @RequestBody Teacher teacher){
        boolean isUpdated = teacherService.updateTeacher(teacherId , teacher);
        if (isUpdated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
