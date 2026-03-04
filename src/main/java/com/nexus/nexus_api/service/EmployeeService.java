package com.nexus.nexus_api.service;

import com.nexus.nexus_api.entity.Employee;
import com.nexus.nexus_api.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    public final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee findByemployeeName(String name) {
        return employeeRepository.findByemployeeName(name)
                .orElseThrow(() -> new RuntimeException("Any employee found with user name: " + name));
    }

    public Employee findByIdOrThrow(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
}
