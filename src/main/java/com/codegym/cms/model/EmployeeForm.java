package com.codegym.cms.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDate;

public class EmployeeForm {


        private Long id;
        private String name;
        private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate birthDate;

    private  float salary;
        private MultipartFile avatar;
        private Department department;



        public EmployeeForm() {
        }

        public EmployeeForm(String name, String address, LocalDate birthDate, float salary, MultipartFile avatar,Department department) {
            this.name = name;
            this.address = address;
            this.birthDate = birthDate;
            this.salary = salary;
            this.avatar = avatar;
            this.department=department;
        }
    public EmployeeForm(Long id,String name, String address, LocalDate birthDate, float salary, MultipartFile avatar,Department department) {
            this.id=id;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.salary = salary;
        this.avatar = avatar;
        this.department=department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}

