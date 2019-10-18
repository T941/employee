package com.codegym.cms.service.impl;

import com.codegym.cms.model.Department;
import com.codegym.cms.repository.DepartmentRepository;
import com.codegym.cms.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;

public class DepartmentServiceImpl  implements DepartmentService {

    @Autowired
    private DepartmentRepository provinceRepository;

    @Override
    public Iterable<Department> findAll() {
        return provinceRepository.findAll();
    }

    @Override
    public Department findById(Long id) {
        return provinceRepository.findOne(id);
    }

    @Override
    public void save(Department department) {
        provinceRepository.save(department);
    }

    @Override
    public void remove(Long id) {
        provinceRepository.delete(id);
    }
}
