package com.nexus.nexus_api.service;

import com.nexus.nexus_api.dto.EmployeeRequestDto;
import com.nexus.nexus_api.dto.EmployeeResponseDto;
import com.nexus.nexus_api.entity.Employee;
import com.nexus.nexus_api.exception.ResourceNotFoundException;
import com.nexus.nexus_api.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeResponseDto findById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
        return toDto(employee);
    }

    public EmployeeResponseDto findByemployeeName(String name) {
        Employee employee = employeeRepository.findByemployeeName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Any employee found with user name: " + name));
        return toDto(employee);
    }

    public Employee findByIdOrThrow(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }

    public List<EmployeeResponseDto> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(this::toDto)
                .toList();
    }

    public EmployeeResponseDto save(@Valid EmployeeRequestDto dto) {
        Employee employee = toEntity(dto);
        Employee savedEmployee = employeeRepository.save(employee);
        return toDto(savedEmployee);
    }

    public EmployeeResponseDto save(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return toDto(savedEmployee);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    private Employee toEntity (EmployeeRequestDto dto){
        Employee employee = new Employee();

        String password = passwordEncoder.encode(dto.getEmployeePassword());

        employee.setEmployeeName(dto.getEmployeeName());
        employee.setEmployeeRole(dto.getEmployeeRole());
        employee.setEmployeeUserName(dto.getEmployeeUserName());
        employee.setEmployeePasswordHash(password);
        employee.setEmployeeSalary(dto.getEmployeeSalary());
        return employee;
    }


    private EmployeeResponseDto toDto(Employee employee) {
        return new EmployeeResponseDto(
                employee.getId(),
                employee.getEmployeeName(),
                employee.getEmployeeRole(),
                employee.isEmployeeIsActive(),
                employee.getEmployeeSalary(),
                employee.getCreatedAt()
        );
    }

}
