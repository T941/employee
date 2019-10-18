package com.codegym.cms.model;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name="departments")
public class Department {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(targetEntity = Employee.class)
    private List<Employee> employees;

    public Department(String name) {
        this.name = name;
    }

    public Department() {
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setCustomers(List<Employee> employees) {
        this.employees = employees;
    }
}
