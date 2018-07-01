package net.corevalue.company.hierarchy.web.controller;

import lombok.AllArgsConstructor;
import net.corevalue.company.hierarchy.domain.model.TopManager;
import net.corevalue.company.hierarchy.service.IEmployeeService;
import net.corevalue.company.hierarchy.service.ISupervisorService;
import net.corevalue.company.hierarchy.web.dto.EmployeeDTO;
import net.corevalue.company.hierarchy.web.dto.EmployeeWithSupervisorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

    IEmployeeService employeeService;
    ISupervisorService supervisorService;

    @GetMapping()
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAll();
    }

    @GetMapping("/{employeeId}")
    public EmployeeWithSupervisorDTO getEmployee(@PathVariable Long employeeId) {
        return employeeService.getByIdWithSupervisor(employeeId);
    }

    @GetMapping("/{employeeId}/ask")
    public TopManager getTopManagerByRole(@PathVariable Long employeeId,
                                          @RequestParam("role") String role) {
        return employeeService.getTopManagerByRole(role, employeeId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void saveEmployee(@RequestBody @NotNull @Valid EmployeeDTO employeeDTO) {
        employeeService.save(employeeDTO);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable Long employeeId) {
        employeeService.delete(employeeId);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{employeeId}")
    public void updateEmployee(@PathVariable Long employeeId,
                               @RequestBody @NotNull @Valid EmployeeDTO employeeDTO) {
        employeeService.update(employeeId, employeeDTO);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{employeeId}/supervisor")
    public void addEmployeeSupervisor(@PathVariable Long employeeId,
                                      @RequestParam("id") Long supervisorId) {
        supervisorService.addSupervisor(employeeId, supervisorId);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{employeeId}/supervisor")
    public void deleteEmployeeSupervisor(@PathVariable Long employeeId) {
        supervisorService.removeSupervisor(employeeId);
    }
}