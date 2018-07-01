package net.corevalue.company.hierarchy.service.pattern;

import lombok.AllArgsConstructor;
import net.corevalue.company.hierarchy.domain.model.Employee;
import net.corevalue.company.hierarchy.exception.ExceptionConstants;
import net.corevalue.company.hierarchy.exception.UserHasSupervisorException;

@AllArgsConstructor
public class UserHasSupervisorMiddleware extends Middleware {

    private Employee subordinateCandidate;
    private Long supervisorCandidateId;

    @Override
    public boolean check() {
        Employee supervisor = subordinateCandidate.getSupervisor();

        if (supervisor == null) {
            return checkNext();
        } else if(supervisor.getId().equals(supervisorCandidateId)) {
            throw new UserHasSupervisorException(ExceptionConstants.YOU_ALREADY_SUPERVISE_ENTITY);
        } else throw new UserHasSupervisorException(ExceptionConstants.ENTITY_HAS_SUPERVISOR);
    }
}