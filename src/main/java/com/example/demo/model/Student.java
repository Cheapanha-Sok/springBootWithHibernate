package com.example.demo.model;

import com.example.demo.model.Department;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Table(name = "Students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private long studentId;
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "address")
    private String address;
    @Column(name = "generation")
    private Integer generation;
    @Column(name = "student_year")
    private Integer studentYear;
    @Column(name = "degree")
    private String degree;
    @Transient
    private Integer age;
    @Column(name = "Dob ")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dob;


    public Student(){

    }
    public Student(Long studentId,String studentName,String gender,LocalDate dob,String phoneNumber,String address,Integer generation,Integer studentYear,String degree) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.gender=gender;
        this.phoneNumber=phoneNumber;
        this.dob = dob;
        this.address = address;
        this.degree=degree;
        this.generation = generation;
        this.studentYear=studentYear;
    }
    public Student(String studentName,String gender,LocalDate dob,String phoneNumber,String address,Integer generation,Integer studentYear,String degree){
        this.studentName = studentName;
        this.gender=gender;
        this.phoneNumber=phoneNumber;
        this.dob = dob;
        this.address = address;
        this.degree=degree;
        this.generation = generation;
        this.studentYear=studentYear;
    }
    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getAge(){
        return Period.between(this.dob,LocalDate.now()).getYears();
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getGeneration() {
        return generation;
    }

    public void setGeneration(Integer generation) {
        this.generation = generation;
    }

    public Integer getStudentYear() {
        return studentYear;
    }

    public void setStudentYear(Integer studentYear) {
        this.studentYear = studentYear;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    public LocalDate getDob(){
        return dob;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    @ManyToMany
    @JoinTable(
            name = "student_enrolled",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id"
            )
    )
    private List<Department> departments;

    @Override
    public String toString(){
        return "Students{"+
                " studentId=" + studentId+
                ", studentName" + studentName+ '\''+
                ", gender=" + gender+'\''+
                ", age=" + age+'\''+
                ", dateOfBirth=" + dob+'\''+
                ", phoneNumber" + phoneNumber+
                ", address" + address+'\''+
                ", studentYear" + studentYear+
                ", generation" + generation+
                ", degree" + degree+
                '}';
    }
    public List<Department> getDepartments() {
        return departments;
    }
}
