package be.community.Api_First_Enterprise.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * CreateEmployeeRequestDTO
 * Permet d'afficher les attributs auxquels l'API First de l'employé demande.
 * Dans ce cas-là, il demande:
 *
 * @param name      Le nom de famille de l'employé
 * @param firstname le prénom de l'employé
 * @param service   Le service de l'employé (ex: RH, IT)
 * @param floor     L'étage du service
 * @param address Donne l'addresse complete de l'employé
 */
public record CreateEmployeeRequestDTO(
        @NotBlank
        String name,
        String firstname,
        String service,
        Integer floor,
        AddressDTO address
) {
}