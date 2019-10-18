package com.codegym.cms.service.impl;


import com.codegym.cms.model.Department;
import com.codegym.cms.model.Employee;
import com.codegym.cms.model.EmployeeForm;
import com.codegym.cms.repository.EmployeeRepository;
import com.codegym.cms.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@PropertySource("classpath:global_config_app.properties")
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    Environment env;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Page findAll(Pageable pageable) {
        return employeeRepository.sort(pageable);
    }

    @Override
    public void delete(long id) {
        employeeRepository.delete(id);
    }

    @Override
    public Employee findById(long id) {
        return employeeRepository.findOne(id);
    }


    @Override
    public void save(EmployeeForm employeeForm) {
        Employee employee = getEmployee(employeeForm);
        // luu vao db
        employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployee(EmployeeForm employeeForm) {
        MultipartFile multipartFile = employeeForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();
        // luu file len server
        try {
            //multipartFile.transferTo(imageFile);
            FileCopyUtils.copy(employeeForm.getAvatar().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (fileName.equals("") && employeeForm.getId() != null){
            Employee employee = findById(employeeForm.getId());
            fileName = employee.getAvatar();
        }

        if (employeeForm.getId() == null) {
            return new Employee(employeeForm.getName(), employeeForm.getAddress(),employeeForm.getBirthDate(),employeeForm.getSalary(),fileName,employeeForm.getDepartment());
        } else {
            return new Employee(employeeForm.getId(),employeeForm.getName(),employeeForm.getAddress(),employeeForm.getBirthDate(),employeeForm.getSalary(),fileName,employeeForm.getDepartment());
        }
    }

    @Override
    public Iterable<Employee> findAllByDepartment(Department department) {
        return employeeRepository.findAllByDepartment(department);
    }

    @Override
    public Page<Employee> findAllByNameContaining(String name, Pageable pageable) {
        return employeeRepository.findAllByNameContaining(name,pageable);
    }


}
