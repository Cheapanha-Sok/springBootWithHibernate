package com.example.demo.course;

import com.example.demo.teacher.Teacher;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "course_credit")
    private Float credit;

    @Column(name = "course_type")

    private String type;

    public Course() {
    }

    public Course(Long courseId, String courseName, float credit, String type) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credit = credit;
        this.type = type;
    }

    public Course(String courseName, float credit, String type) {
        this.courseName = courseName;
        this.credit = credit;
        this.type = type;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCredit(Float credit) {
        this.credit = credit;
    }

    public void setType(String type) {
        this.type = type;
    }
    public Long getCourseId(){
        return courseId;
    }
    public void setCourseId(Long courseId){
        this.courseId=courseId;
    }
    public String getCourseName() {
        return courseName;
    }

    public Float getCredit() {
        return credit;
    }

    public String getType() {
        return type;
    }

    @ManyToMany
    @JoinTable(
            name = "AssignCourse",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private List<Teacher> teachers;

    @Override
    public String toString() {
        return "Course{" +
                " Course Id=" + courseId +
                ", Course Name" + courseName + '\'' +
                ", Course Credit=" + credit + '\'' +
                ", Office type" + type +
                '}';
    }
}