package com.david.kleinman.work_time_logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.david.kleinman.work_time_logging.exceptions.EmployeeException;

@RestController
public class Controller {
    private final Service service;

	@Autowired
	public Controller(Service service) {
		this.service = service;
	}

    @PutMapping("/enter")
	public void enterEmployee(@RequestParam Long id) {
		try {
			this.service.enterEmployee(id);	
		} catch (EmployeeException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@PutMapping("/exit")
	public void exitEmployee(@RequestParam Long id) {
		try {
			this.service.exitEmployee(id);
		} catch (EmployeeException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@GetMapping("/info")
	public Object getEmployeeInfo(@RequestParam(required = false) Long id) {
		return id == null ? this.service.getAllEmployeesInfo() : this.service.getEmployeeInfo(id);
	}
}
