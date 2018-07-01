package net.corevalue.company.hierarchy.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ControlLockException extends RuntimeException {

    public ControlLockException() {
        log.error(ExceptionConstants.CONTROL_LOCK);
    }
}