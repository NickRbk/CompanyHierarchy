package net.corevalue.company.hierarchy.service.pattern;

import lombok.AllArgsConstructor;
import net.corevalue.company.hierarchy.exception.IdEqualsException;

@AllArgsConstructor
public class CheckIdMiddleware extends Middleware {

    private Long subordinateId, supervisorId;

    @Override
    public boolean check() {
        if (subordinateId.equals(supervisorId)) {
            throw new IdEqualsException();
        } else return checkNext();
    }
}