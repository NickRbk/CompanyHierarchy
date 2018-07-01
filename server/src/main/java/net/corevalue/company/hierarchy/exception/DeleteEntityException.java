package net.corevalue.company.hierarchy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = ExceptionConstants.DELETE_CONFLICT)
public class DeleteEntityException extends RuntimeException {
}