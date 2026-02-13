package be.community.Api_First_Enterprise.dto;

public record UpdateEmployeeRequestDTO(
        String name,
        String firstname,
        String service,
        Integer floor
) {
}
