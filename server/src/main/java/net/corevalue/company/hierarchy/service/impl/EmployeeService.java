package net.corevalue.company.hierarchy.service.impl;

import lombok.AllArgsConstructor;
import net.corevalue.company.hierarchy.domain.model.Employee;
import net.corevalue.company.hierarchy.domain.model.TopManager;
import net.corevalue.company.hierarchy.domain.repository.EmployeeRepository;
import net.corevalue.company.hierarchy.domain.repository.TopManagerRepository;
import net.corevalue.company.hierarchy.exception.DeleteEntityException;
import net.corevalue.company.hierarchy.exception.NotFoundException;
import net.corevalue.company.hierarchy.service.IEmployeeService;
import net.corevalue.company.hierarchy.web.dto.EmployeeDTO;
import net.corevalue.company.hierarchy.web.dto.EmployeeWithSupervisorDTO;
import net.corevalue.company.hierarchy.web.dto.EntityAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService implements IEmployeeService {

    private EmployeeRepository employeeRepository;
    private TopManagerRepository topManagerRepository;

    @Override
    @Transactional
    public List<EmployeeDTO> getAll() {
        List<Employee> employees = employeeRepository.getAll()
                .collect(Collectors.toList());
        return EntityAdapter.getEmployeeDTOList(employees);
    }

    @Override
    public EmployeeWithSupervisorDTO getByIdWithSupervisor(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(NotFoundException::new);
        return EntityAdapter.getEmployeeWithSupervisorDTOFromEmployee(employee);
    }

    @Override
    public void save(EmployeeDTO employeeDTO) {
        employeeRepository.save(EntityAdapter.getEmployeeFromDTO(null, employeeDTO));
    }

    @Override
    public void update(Long employeeId, EmployeeDTO employeeDTO) {
        if (employeeRepository.findById(employeeId).isPresent()) {
            employeeRepository.save(EntityAdapter.getEmployeeFromDTO(employeeId, employeeDTO));
        } else throw new NotFoundException();
    }

    @Override
    @Transactional
    public void delete(Long employeeId) {
        if (employeeRepository.findById(employeeId).isPresent()
                && employeeRepository.getAllBySupervisorId(employeeId).count() == 0) {
            employeeRepository.deleteById(employeeId);
        } else throw new DeleteEntityException();
    }

    @Override
    public TopManager getTopManagerByRole(String role, Long employeeId) {
        if (employeeRepository.findById(employeeId).isPresent()) {
            return topManagerRepository.findByRole(role)
                    .orElseThrow(NotFoundException::new);
        } else throw new NotFoundException();
    }
}