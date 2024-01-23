package com.example.demo.faculty;

import com.example.demo.department.Department;
import jakarta.persistence.*;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Entity
@Table(name = "Facultys")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faculty_id")
    private Long facultyId;
    @Column(name = "faculty_name")
    private String facultyName;
    @Column(name = "deanName")
    private String deanName;
    @Column(name = "office_number")
    private Integer officeNumber;

    public Faculty(){
    }
    public Faculty(Long facultyId , String facultyName ,String deanName, Integer officeNumber){
        this.facultyId=facultyId;
        this.facultyName=facultyName;
        this.deanName = deanName;
        this.officeNumber=officeNumber;
    }
    public Faculty(String facultyName ,String deanName, Integer officeNumber){
        this.facultyName=facultyName;
        this.deanName=deanName;
        this.officeNumber=officeNumber;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public Integer getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(Integer officeNumber) {
        this.officeNumber = officeNumber;
    }
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "faculty")
    private List<Department> departments;

    public String getDeanName() {
        return deanName;
    }

    public void setDeanName(String deanName) {
        this.deanName = deanName;
    }
    @Override
    public String toString(){
        return "faculty{"+
                " facultyId=" + facultyId+
                ", facultyName=" + facultyName+ '\''+
                ", deanName=" + deanName+'\''+
                ", officeNumber=" + officeNumber+
                '}';
    }

}
