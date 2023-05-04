package com.david.kleinman.work_time_logging;

import com.david.kleinman.work_time_logging.employee.Employee;
import com.david.kleinman.work_time_logging.exceptions.EmployeeException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Service
public class Service {
    private Map<Long ,Employee> employees = new HashMap<>();

	public void enterEmployee(Long employeeId) throws EmployeeException {
        if (this.employees.keySet().contains(employeeId)) {
            this.employees.get(employeeId).enter();
        } else {
            Employee employee = new Employee(employeeId);
            employee.enter();
            this.employees.put(employeeId, employee);
        }	
	}

	public void exitEmployee(Long employeeId) throws EmployeeException {
        if (this.employees.get(employeeId) == null) {
            throw new EmployeeException("There is no such employee!");
        }
        this.employees.get(employeeId).exit();
	}

	public Collection<Employee> getAllEmployeesInfo() {
		return this.employees.values();
	}

    public Employee getEmployeeInfo(Long employeeId) {
        return this.employees.get(employeeId);
    }
}
