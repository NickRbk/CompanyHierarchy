package net.corevalue.company.hierarchy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = ExceptionConstants.NOT_FOUND)
public class NotFoundException extends RuntimeException {
}