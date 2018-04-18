package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // http://localhost:8080/employee/test
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    String test() {
        System.out.println("Welcome");
        return "nikita";

    }

    // http://localhost:8080/employee/list
    @RequestMapping(value = "/list")
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }


//	 http://localhost:8080/employee/add POST
//	{
//		"empId": 2,
//			"empName": "mytest",
//			"empAddress": "test@gmail.com"
//	}
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public void addEmployee(@RequestBody Employee emp) {
        employeeService.addEmployee(emp);
    }


    // http://localhost:8080/employee/emp/2
    @GetMapping("/emp/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id") Integer id) {
        return employeeService.findById(id);

    }

//	 http://localhost:8080/employee/update/2 POST
//	{
//		"empId": 2,
//			"empName": "mytestUpdate",
//			"empAddress": "test@gmail.com"
//	}
    @RequestMapping(method = RequestMethod.POST, value = "/update/{id}")
    public Employee updateEmployee(@PathVariable(value = "id") Integer id,
                                   @RequestBody Employee e) {
        Employee emp = employeeService.findById(id);
        emp.setEmpName(e.getEmpName());
        emp.setEmpAddress(e.getEmpAddress());
        Employee empUpdate = employeeService.updateEmp(emp);
        return empUpdate;

    }

//  http://localhost:8080/employee/emp/1
    @RequestMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable(value = "id") Integer id) {
        employeeService.deleteEmp(id);

    }
}
