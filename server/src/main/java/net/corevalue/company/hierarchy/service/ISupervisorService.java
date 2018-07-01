package net.corevalue.company.hierarchy.service;

import net.corevalue.company.hierarchy.web.dto.EmployeeDTO;

import java.util.List;

public interface ISupervisorService {

    List<EmployeeDTO> getSupervisors();

    List<EmployeeDTO> getSubordinatesBySupervisorId(Long supervisorId);

    void addSupervisor(Long employeeId, Long supervisorId);

    void removeSupervisor(Long employeeId);
}