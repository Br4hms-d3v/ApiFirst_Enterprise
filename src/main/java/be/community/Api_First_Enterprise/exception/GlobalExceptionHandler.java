package be.community.Api_First_Enterprise.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleEmployeeNotFoundException(EmployeeNotFoundException ex, HttpServletRequest request) {
        Map<String,Object> problem = new HashMap<>();
        problem.put("type", "https://example.com/errors/employee-not-found");
        problem.put("title", "Employee Not Found");
        problem.put("status", 404);
        problem.put("detail", ex.getMessage());
        problem.put("instance", request.getRequestURI());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }
}
