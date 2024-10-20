package id.co.mii.serverapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.services.EmployeeService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {

  private EmployeeService employeeService;

  @GetMapping
  public List<Employee> getAll() {
    return employeeService.getAll();
  }

  @GetMapping("/{id}")
  public Employee getById(@PathVariable Integer id) {
    return employeeService.getById(id);
  }

  @PostMapping
  public Employee create(@RequestBody Employee employee) {
    return employeeService.create(employee);
  }

  @PutMapping("/{id}")
  public Employee update(
      @PathVariable Integer id,
      @RequestBody Employee employee) {
    return employeeService.update(id, employee);
  }

  @DeleteMapping("/{id}")
  public Employee delete(@PathVariable Integer id) {
    return employeeService.delete(id);
  }
}