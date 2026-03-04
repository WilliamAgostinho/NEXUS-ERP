    package com.nexus.nexus_api.controller;

    import com.nexus.nexus_api.entity.Employee;
    import com.nexus.nexus_api.service.EmployeeService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/employees")
    public class EmployeeController {

        @Autowired
        private EmployeeService employeeService;

        @GetMapping("/")
        public List<Employee> findAll() {
            return employeeService.findAll();
        }

        @GetMapping("/employeeid/{id}")
        public Employee findById(@PathVariable Long id) {
            return (Employee) employeeService.findByIdOrThrow(id);
        }

        @GetMapping("/employeename/{name}")
        public Employee findByEmployeeName(@PathVariable String name) {
            return (Employee) employeeService.findByemployeeName(name);
        }

        @PostMapping("/createemployee")
        public Employee save(@RequestBody Employee employee) {
            return employeeService.save(employee);
        }

        @PutMapping("/updateemployee/{id}")
        public Employee update(@PathVariable Long id, @RequestBody Employee employee) {
            Employee existingEmployee = employeeService.findByIdOrThrow(id);

            existingEmployee.setEmployeeName(employee.getEmployeeName());
            existingEmployee.setEmployeeRole(employee.getEmployeeRole());
            existingEmployee.setEmployeeSalary(employee.getEmployeeSalary());
            existingEmployee.setEmployeeUserName(employee.getEmployeeUserName());
            existingEmployee.setEmployeeIsActive(employee.isEmployeeIsActive());

            return employeeService.save(existingEmployee);
        }


        @DeleteMapping("/deleteemployee/{id}")
        public void delete(@PathVariable Long id) {
            employeeService.deleteById(id);
        }
    }
