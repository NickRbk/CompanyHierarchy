package net.corevalue.company.hierarchy.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "net.corevalue.company.hierarchy.web.controller")
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "CAN'T ADD SUPERVISOR. FOR MORE INFO SEE LOGS!")
    @ExceptionHandler(value = {IdEqualsException.class, UserHasSupervisorException.class, ControlLockException.class})
    public void handleInvalidDataExceptions() {
        log.error("====== ERROR IN CHAIN ======");
    }
}