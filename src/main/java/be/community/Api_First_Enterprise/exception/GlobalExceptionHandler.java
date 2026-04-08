package be.community.Api_First_Enterprise.exception;


import org.openapitools.model.InputValidationIssue;
import org.openapitools.model.InputValidationProblem;
import org.openapitools.model.Problem;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestControllerAdvice
@ConditionalOnWebApplication
public class GlobalExceptionHandler {

    public static final String BELGIF_BAD_REQUEST = "urn:problem-type:belgif:badRequest";
    public static final String BELGIF_BAD_REQUEST_TITLE = "Bad Request";
    public static final String BELGIF_RANDOM_UUID = "urn:uuid";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Problem> handlerGenericException(Exception exception) {
        Problem problem = new Problem()
                .type(URI.create("urn:problem-type:belgif:internalServerError")) // get a type
                .title("Internal Server Error") // get a title
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value()) // get the status
                .detail(exception.getMessage()) // get a detail
                .instance(URI.create(BELGIF_RANDOM_UUID + UUID.randomUUID())); // get a UUID

        return new ResponseEntity<>(problem, HttpStatus.INTERNAL_SERVER_ERROR); // return a problem with status
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<InputValidationProblem> handleValidationProblem(MethodArgumentNotValidException exception) {
        List<InputValidationIssue> issues = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> new InputValidationIssue()
                        .type(URI.create("urn:problem-type:belgif:input-validation:schemaViolation"))
                        .title("Invalid Input")
                        .detail(error.getDefaultMessage())
                        .in(InputValidationIssue.InEnum.BODY)
                        .name(error.getField())
                        .value(error.getRejectedValue()))
                .toList();

        InputValidationProblem problem = new InputValidationProblem()
                .type(URI.create(BELGIF_BAD_REQUEST))
                .title(BELGIF_BAD_REQUEST_TITLE)
                .status(HttpStatus.BAD_REQUEST.value())
                .detail("Invalid input parameters")
                .instance(URI.create(BELGIF_RANDOM_UUID + UUID.randomUUID()))
                .issues(issues);

        return new ResponseEntity<>(problem, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(EmployeeNameMustNotEmptyException.class)
    public ResponseEntity<Problem> handleEmployeeNameMustNotEmptyException(Exception exception) {
        Problem problem = new Problem()
                .type(URI.create(BELGIF_BAD_REQUEST))
                .title(BELGIF_BAD_REQUEST_TITLE)
                .status(HttpStatus.BAD_REQUEST.value())
                .detail("Invalid input parameters")
                .instance(URI.create(BELGIF_RANDOM_UUID + UUID.randomUUID()));

        return new ResponseEntity<>(problem, HttpStatus.BAD_REQUEST);
    }

}
