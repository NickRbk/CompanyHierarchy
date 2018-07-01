package net.corevalue.company.hierarchy.web.dto;

import net.corevalue.company.hierarchy.domain.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EntityAdapter {

    public static Employee getEmployeeFromDTO(Long employeeId,
                                              EmployeeDTO employeeDTO) {
        return Employee.builder()
                .id(employeeId)
                .firstName(employeeDTO.getFirstName())
                .familyName(employeeDTO.getFamilyName())
                .phoneNumber(employeeDTO.getPhoneNumber())
                .position(employeeDTO.getPosition())
                .build();
    }

    public static EmployeeWithSupervisorDTO getEmployeeWithSupervisorDTOFromEmployee(Employee employee) {
        EmployeeDTO supervisorDTO = employee.getSupervisor() != null
                ? getDTOFromEmployee(employee.getSupervisor())
                : null;

        return EmployeeWithSupervisorDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .familyName(employee.getFamilyName())
                .phoneNumber(employee.getPhoneNumber())
                .position(employee.getPosition())
                .supervisor(supervisorDTO)
                .build();
    }

    public static List<EmployeeDTO> getEmployeeDTOList(List<Employee> employees) {
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        employees.forEach(employee -> employeeDTOs.add(getDTOFromEmployee(employee)));
        return employeeDTOs;
    }

    private static EmployeeDTO getDTOFromEmployee(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .familyName(employee.getFamilyName())
                .phoneNumber(employee.getPhoneNumber())
                .position(employee.getPosition())
                .build();
    }
}
