package nti.team.test.adviceController;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class RestController {

    @ExceptionHandler(value = EntityNotFoundException.class)
    public String validEntityNotFoundException(EntityNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public String validIllegalArgumentException(IllegalArgumentException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(value = EntityExistsException.class)
    public String validEntityExistsException(EntityExistsException exception) {
        return exception.getMessage();
    }

}
