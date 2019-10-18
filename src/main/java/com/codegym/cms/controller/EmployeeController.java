
package com.codegym.cms.controller;


import com.codegym.cms.model.Department;
import com.codegym.cms.model.Employee;
import com.codegym.cms.model.EmployeeForm;
import com.codegym.cms.service.DepartmentService;
import com.codegym.cms.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @ModelAttribute("departments")
    public Iterable<Department> departments(){
        return departmentService.findAll();
    }

    @GetMapping("/create-employee")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/employee/create");
        modelAndView.addObject("employee", new EmployeeForm());
        return modelAndView;
    }

    @PostMapping("/create-employee")
    public ModelAndView saveCustomer(@ModelAttribute EmployeeForm employeeForm){
        employeeService.save(employeeForm);

        ModelAndView modelAndView = new ModelAndView("/employee/create");
        modelAndView.addObject("employee", new EmployeeForm());
        modelAndView.addObject("message", "New employee created successfully");
        return modelAndView;
    }
    //list
    @GetMapping("/list")
    public ModelAndView listEmployee(@RequestParam("s") Optional<String> s, @PageableDefault(value = 3) Pageable pageable){
        Page<Employee> employees;
        if(s.isPresent()){
            employees = employeeService.findAllByNameContaining(s.get(), pageable);
        } else {
            employees = employeeService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/employee/list");
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }

    //update
    @GetMapping("/employee/edit/{id}")
    public ModelAndView showEditForm(@PathVariable long id){
        Employee employee = employeeService.findById(id);
        if(employee != null) {
            ModelAndView modelAndView = new ModelAndView("/employee/edit");
            modelAndView.addObject("employee", employee);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }


    @PostMapping("/employee/edit/")
    public String updateEmployee(@Validated @ModelAttribute EmployeeForm employeeForm, BindingResult result){
        if (!result.hasFieldErrors()) {
            employeeService.save(employeeForm);
        }
        return "redirect:/list";
    }

    @GetMapping("/delete-employee/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Employee employee = employeeService.findById(id);
        if(employee != null) {
            ModelAndView modelAndView = new ModelAndView("/employee/delete");
            modelAndView.addObject("employee", employee);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-employee")
    public String deleteProvince(@ModelAttribute EmployeeForm employee){
        employeeService.delete(employee.getId());
        return "redirect:list";
    }


}