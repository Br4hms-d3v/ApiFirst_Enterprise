package be.community.Api_First_Enterprise.dto;

import be.community.Api_First_Enterprise.entities.Address;

public record CreateEmployeeRequestDTO(
        String name,
        String firstname,
        String service,
        Integer floor,
        Address address
) {
}
