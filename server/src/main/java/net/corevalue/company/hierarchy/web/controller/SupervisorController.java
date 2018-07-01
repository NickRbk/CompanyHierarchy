package net.corevalue.company.hierarchy.web.controller;

import lombok.AllArgsConstructor;
import net.corevalue.company.hierarchy.service.ISupervisorService;
import net.corevalue.company.hierarchy.web.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/supervisors")
@AllArgsConstructor
public class SupervisorController {

    ISupervisorService supervisorService;

    @GetMapping()
    public List<EmployeeDTO> getAllSupervisors() {
        return supervisorService.getSupervisors();
    }

    @GetMapping("/{supervisorId}")
    public List<EmployeeDTO> getAllSubordinates(@PathVariable Long supervisorId) {
        return supervisorService.getSubordinatesBySupervisorId(supervisorId);
    }
}