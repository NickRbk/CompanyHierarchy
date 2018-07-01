package net.corevalue.company.hierarchy.service;

import net.corevalue.company.hierarchy.domain.model.TopManager;
import net.corevalue.company.hierarchy.web.dto.EmployeeDTO;
import net.corevalue.company.hierarchy.web.dto.EmployeeWithSupervisorDTO;

import java.util.List;

public interface IEmployeeService {

    List<EmployeeDTO> getAll();

    EmployeeWithSupervisorDTO getByIdWithSupervisor(Long employeeId);

    void save(EmployeeDTO employeeDTO);

    void update(Long employeeId, EmployeeDTO employeeDTO);

    void delete(Long employeeId);

    TopManager getTopManagerByRole(String role, Long employeeId);
}