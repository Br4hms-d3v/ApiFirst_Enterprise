package be.community.Api_First_Enterprise.exception;

public class EmployeeNameMustNotEmptyException extends RuntimeException {
    public EmployeeNameMustNotEmptyException(String message) {
        super(message);
    }
}
