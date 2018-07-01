package net.corevalue.company.hierarchy.service.impl;

import lombok.AllArgsConstructor;
import net.corevalue.company.hierarchy.domain.model.Employee;
import net.corevalue.company.hierarchy.domain.repository.EmployeeRepository;
import net.corevalue.company.hierarchy.exception.NotFoundException;
import net.corevalue.company.hierarchy.service.ISupervisorService;
import net.corevalue.company.hierarchy.service.pattern.CheckIdMiddleware;
import net.corevalue.company.hierarchy.service.pattern.ControlLockMiddleware;
import net.corevalue.company.hierarchy.service.pattern.Middleware;
import net.corevalue.company.hierarchy.service.pattern.UserHasSupervisorMiddleware;
import net.corevalue.company.hierarchy.web.dto.EmployeeDTO;
import net.corevalue.company.hierarchy.web.dto.EntityAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SupervisorService implements ISupervisorService {

    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public List<EmployeeDTO> getSupervisors() {
        List<Employee> supervisors = employeeRepository.getAllSupervisors()
                .collect(Collectors.toList());
        return EntityAdapter.getEmployeeDTOList(supervisors);
    }

    @Override
    @Transactional
    public List<EmployeeDTO> getSubordinatesBySupervisorId(Long supervisorId) {
        if (employeeRepository.findById(supervisorId).isPresent()) {
            List<Employee> subordinates = employeeRepository.getAllBySupervisorId(supervisorId)
                    .collect(Collectors.toList());
            return EntityAdapter.getEmployeeDTOList(subordinates);
        } else throw new NotFoundException();
    }

    @Override
    @Transactional
    public void addSupervisor(Long subordinateId, Long supervisorId) {
        Middleware middleware = getMiddleware(subordinateId, supervisorId);
        if (middleware.check()) {
            employeeRepository.addSupervisor(subordinateId, supervisorId);
        }
    }

    @Override
    @Transactional
    public void removeSupervisor(Long employeeId) {
        if (employeeRepository.findById(employeeId).isPresent()) {
            employeeRepository.removeSupervisor(employeeId);
        } else throw new NotFoundException();
    }

    private Middleware getMiddleware(Long subordinateId, Long supervisorId) {
        Employee subordinateCandidate = employeeRepository.findById(subordinateId)
                .orElseThrow(NotFoundException::new);

        Employee supervisorCandidate = employeeRepository.findById(supervisorId)
                .orElseThrow(NotFoundException::new);

        Middleware middleware = new CheckIdMiddleware(subordinateId, supervisorId);
        middleware.then(new UserHasSupervisorMiddleware(subordinateCandidate, supervisorCandidate.getId()))
                .then(new ControlLockMiddleware(subordinateCandidate, supervisorCandidate));

        return middleware;
    }
}