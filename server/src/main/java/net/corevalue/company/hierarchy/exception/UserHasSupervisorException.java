package net.corevalue.company.hierarchy.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserHasSupervisorException extends RuntimeException {

    public UserHasSupervisorException(String msg) {
        log.error(msg);
    }
}