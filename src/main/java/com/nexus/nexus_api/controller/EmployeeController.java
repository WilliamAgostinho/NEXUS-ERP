    package com.nexus.nexus_api.controller;

    import com.nexus.nexus_api.dto.EmployeeRequestDto;
    import com.nexus.nexus_api.dto.EmployeeResponseDto;
    import com.nexus.nexus_api.entity.Employee;
    import com.nexus.nexus_api.service.EmployeeService;
    import jakarta.validation.Valid;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/employees")
    public class EmployeeController {

        private final EmployeeService employeeService;

        public EmployeeController(EmployeeService employeeService) {
            this.employeeService = employeeService;
        }

        @GetMapping
        public List<EmployeeResponseDto> findAll() {
            return employeeService.findAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<EmployeeResponseDto> findById(@PathVariable Long id) {
            EmployeeResponseDto response = employeeService.findById(id);
            return ResponseEntity.ok(response);
        }

        @GetMapping("/name/{name}")
        public ResponseEntity<EmployeeResponseDto> findByEmployeeName(@PathVariable String name) {
            EmployeeResponseDto response = employeeService.findByemployeeName(name);
            return ResponseEntity.ok(response);
        }

        @PostMapping
        public ResponseEntity<EmployeeResponseDto> createEmployee(@RequestBody @Valid EmployeeRequestDto dto) {
            EmployeeResponseDto response = employeeService.save(dto);
            return ResponseEntity.status(201).body(response);
        }

        @PutMapping("/{id}")
        public ResponseEntity<EmployeeResponseDto> update(@PathVariable Long id, @RequestBody @Valid EmployeeRequestDto dto) {

            Employee existingEmployee = employeeService.findByIdOrThrow(id);

            existingEmployee.setEmployeeName(dto.getEmployeeName());
            existingEmployee.setEmployeeRole(dto.getEmployeeRole());
            existingEmployee.setEmployeeSalary(dto.getEmployeeSalary());
            existingEmployee.setEmployeeIsActive(dto.getEmployeeIsActive());

            return ResponseEntity.ok(employeeService.save(existingEmployee));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {
            employeeService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }
