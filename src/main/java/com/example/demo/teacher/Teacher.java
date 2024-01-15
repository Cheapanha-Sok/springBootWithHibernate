package com.example.demo.teacher;

import com.example.demo.course.Course;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Table(name = "Teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long teacherId;
    @Column(name = "teacher_name")
    private String teacherName;
    @Column(name = "gender")
    private String gender;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "Dob")
    private LocalDate dob;
    @Transient
    private Integer age;
    @Column(name = "address")
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    public Teacher(){

    }
    public Teacher(Long teacherId , String teacherName , String gender
            , LocalDate dob ,String address, String phoneNumber){
        this.teacherId=teacherId;
        this.teacherName = teacherName;
        this.gender=gender;
        this.dob=dob;
        this.address=address;
        this.phoneNumber=phoneNumber;
    }
    public Teacher( String teacherName , String gender
            , LocalDate dob ,String address, String phoneNumber){
        this.teacherName = teacherName;
        this.gender=gender;
        this.dob=dob;
        this.address=address;
        this.phoneNumber=phoneNumber;
    }


    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Integer getAge() {
        return Period.between(this.dob,LocalDate.now()).getYears();
    }
    @ManyToMany
    @JoinTable(name = "AssignCourse",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns =@JoinColumn (name = "course_id"))
    private List<Course>courses;

    @Override
    public String toString(){
        return "Teacher{"+
                " teacherId=" + teacherId+
                ", teacherName" + teacherName+ '\''+
                ", Gender=" + gender+'\''+
                ", Date of birth=" + dob+'\''+
                ", Age=" + age+'\''+
                ", Address" + address+'\''+
                ", Phone Number" + phoneNumber+
                ", Course Id" + courses+
                '}';
    }


    public void setCourse(Course courseId) {
        this.courses.add(courseId);
    }

    public List<Course> getCourses() {
        return courses;
    }
}
