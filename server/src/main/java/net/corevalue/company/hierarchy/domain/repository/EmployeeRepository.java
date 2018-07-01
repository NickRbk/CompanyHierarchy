package net.corevalue.company.hierarchy.domain.repository;

import net.corevalue.company.hierarchy.domain.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT * FROM employees", nativeQuery = true)
    Stream<Employee> getAll();

    @Query(value = "SELECT DISTINCT e.supervisor FROM Employee e WHERE e.supervisor IS NOT NULL")
    Stream<Employee> getAllSupervisors();

    Stream<Employee> getAllBySupervisorId(Long supervisorId);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE employees SET supervisor_id =:supervisorId WHERE id =:employeeId", nativeQuery = true)
    void addSupervisor(@Param("employeeId") Long employeeId, @Param("supervisorId") Long supervisorId);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE employees SET supervisor_id = NULL WHERE id =:employeeId", nativeQuery = true)
    void removeSupervisor(@Param("employeeId") Long employeeId);
}