# Work Time Logging System
This repository contains an API for logging working times of employees.
The domain of the API is `ec2-13-53-188-254.eu-north-1.compute.amazonaws.com` listening on port 80.
The API answers to the following API requests:
- PUT `ec2-13-53-188-254.eu-north-1.compute.amazonaws.com:80/enter?id=***`
The server will log entrance time for the employee with id=***.
- PUT `ec2-13-53-188-254.eu-north-1.compute.amazonaws.com:80/exit?id=***`
The server will log exit time for employee with id=***.
- GET `ec2-13-53-188-254.eu-north-1.compute.amazonaws.com:80/info?id=***`
The server will respond with a json object containing all enter and exit times for the employee with id=***.
- GET `ec2-13-53-188-254.eu-north-1.compute.amazonaws.com:80/info`
The server will respond with a json object containing all enter and exit times for all employees in the system.

Server definitions

- If 2 consecutive enter/exit requests arrive, the second request returns an error. (http code: 500).
- If an exit request arrives without an earlier enter request, the request returns an error. (http code: 500).
- If an enter request arrives for a non existing worker id, such id is created.