package com.example.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long departmentId;
    @Column(name = "department_name")
    private String departmentName;
    @Column(name = "headName")
    private String headName;
    @Column(name = "office_number")
    private Integer officeNumber;
    public Department(){
    }
    public Department(Long departmentId , String departmentName, String headName, Integer officeNumber){
        this.departmentId = departmentId;
        this.departmentName=departmentName;
        this.headName=headName;
        this.officeNumber = officeNumber;
    }
    public Department(String departmentName , String headName , Integer officeNumber){
        this.departmentName= departmentName;
        this.headName=headName;
        this.officeNumber=officeNumber;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public Integer getOfficeNumber() {
        return officeNumber;
    }
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public void setOfficeNumber(Integer officeNumber) {
        this.officeNumber = officeNumber;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id" , referencedColumnName = "faculty_id")
    private Faculty faculty;

    @ManyToMany(mappedBy = "departments")
    private List<Student> students;

    @ManyToMany(mappedBy = "departments")
    private List<Course> courses;

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", headName='" + headName + '\'' +
                ", officeNumber=" + officeNumber +
                '}';
    }
}
