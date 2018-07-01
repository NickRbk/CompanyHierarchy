# Company Hierarchy

This web app shows the company's employees and provide ability to manipulate 
them, sets supervisor and subordinates.

## Overview

App provide ability to manipulate the company's hierarchy and get information
about human resources.
There are two main role for EMPLOYEES: _**subordinate**_ and _**supervisor**_.
Apart employees in another table there are a TOP MANAGEMENT of company (like CEO etc.).

Initially DB has pre populated data.

There are 2 main controllers:
- `/employees`
- `/supervisors`

### Employees controller (`/employees`)

- `/` (GET) - get all company's employees
```json
[
    {
        "id": 1,
        "firstName": "******",
        "familyName": "******",
        "phoneNumber": "******",
        "position": "******"
    }
]
```

- `/{employeeId}` (GET) - get information about employee (with his supervisor) by id

```json
{
    "id": 1,
    "firstName": "******",
    "familyName": "******",
    "phoneNumber": "******",
    "position": "******",
    "supervisor": {
        "id": 1,
        "firstName": "******",
        "familyName": "******",
        "phoneNumber": "******",
        "position": "******"
    }
}
```

- `/{employeeId}/ask?role={TOP_MANAGER_ROLE}` (GET) - get information about company's
Top Management by role for particular employee (e.g in DB prepopulated role "_**ceo**_")

```json
{
    "id": 1,
    "firstName": "******",
    "familyName": "******",
    "phoneNumber": "******",
    "role": "ceo",
    "roleDescription": "******"
}
```

- `/` (POST) - save new employee

```json
{
	"firstName": "******",
	"familyName": "******",
	"phoneNumber": "******",
	"position": "******"
}
```

- `/{employeeId}` (PATCH) - update information about employee

```json
{
	"firstName": "******",
	"familyName": "******",
	"phoneNumber": "******",
	"position": "******"
}
```

- `/{employeeId}` (DELETE) - delete employee by id

_Also on this controller exist option to **set/remove employee's supervisor**_.

- `/{employeeId}/supervisor?id={SUPERVISOR_ID}` (PATCH) - set supervisor for particular 
employee by id

- `/{employeeId}/supervisor` (DELETE) - remove supervisor for particular employee

:exclamation: _**To set supervisor should be followed the next rules**_:
1. You can't supervise yourself
2. If employee already has supervisor first you need to solve this conflict (detach them)
3. Employees shouldn't control each other. It's control lock issue and it prohibited

The above rules checked in programm by using _**chain of responsibility**_ pattern. 

:exclamation: _**To delete employee should be followed the next rule**_:
- if employee supervised another employees he can't be deleted, before solving this
issue.

### Supervisors controller (`/supervisors`)

- `/` (GET) - get all company's supervisors

```json
[
    {
        "id": 1,
        "firstName": "******",
        "familyName": "******",
        "phoneNumber": "******",
        "position": "******"
    }
]
```

- `/{supervisorId}` (GET) - get all subordinates of particular supervisor

```json
[
    {
        "id": 1,
        "firstName": "******",
        "familyName": "******",
        "phoneNumber": "******",
        "position": "******"
    }
]
```

## Prerequisites
- app should be run with "dev" profile
- start postgreSQL and write actual info into `resources/application-dev.yml`.

## How to start app?
1) download project `git clone https://github.com/NickRbk/CompanyHierarchy.git`
2) enter to downloaded folder and run `cd server`
3) run cmd `mvn spring-boot:run -Dspring.profiles.active=dev`