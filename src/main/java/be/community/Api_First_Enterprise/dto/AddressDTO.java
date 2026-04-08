package be.community.Api_First_Enterprise.dto;

public record AddressDTO(
        String street,
        Integer zipcode,
        String city
) {
}
