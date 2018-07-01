package net.corevalue.company.hierarchy.service.pattern;

import lombok.AllArgsConstructor;
import net.corevalue.company.hierarchy.domain.model.Employee;
import net.corevalue.company.hierarchy.exception.ControlLockException;

@AllArgsConstructor
public class ControlLockMiddleware extends Middleware {

    private Employee subordinateCandidate;
    private Employee supervisorCandidate;

    @Override
    public boolean check() {
        if (supervisorCandidate.getSupervisor() != null
                && supervisorCandidate.getSupervisor().getId().equals(subordinateCandidate.getId())) {
            throw new ControlLockException();
        } else return checkNext();
    }
}