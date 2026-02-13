package be.community.Api_First_Enterprise.dto;

public record EmployeeResponseDTO(
        String name,
        String firstname,
        String service,
        Integer floor
) {
}
