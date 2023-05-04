package com.david.kleinman.work_time_logging.employee;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.david.kleinman.work_time_logging.exceptions.EmployeeException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = {"employee_id", "dates"})
public class Employee {
    private Long employeeId;
    private List<List<String>> dates;

    public Employee(Long employeeId) {
        this.employeeId = employeeId;
        this.dates = new ArrayList<List<String>>();
    }

    @JsonProperty("employee_id")
    public Long getEmployeeId() {
        return this.employeeId;
    }

    public List<List<String>> getDates() {
        return this.dates;
    }

    private String getFormattedTime() {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH:mm");
        return time.format(formatObj);
    }

    public void enter() throws EmployeeException {
        if (!this.dates.isEmpty() && this.dates.get(this.dates.size() - 1).size() == 1) {
            throw new EmployeeException("Employee is already at work!");
        }
        this.dates.add(new ArrayList<>(Arrays.asList(getFormattedTime())));
    }

    public void exit() throws EmployeeException {
        if (this.dates.isEmpty() || this.dates.get(this.dates.size() - 1).size() == 2) {
            throw new EmployeeException("Employee is not at work!");
        }
        this.dates.get(this.dates.size() - 1).add(getFormattedTime());
    }
}
