package net.corevalue.company.hierarchy.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IdEqualsException extends RuntimeException {

    public IdEqualsException() {
        log.error(ExceptionConstants.ID_EQUALITY);
    }
}