package be.community.Api_First_Enterprise.dto;

/**
 * EmployeesResponseDTO
 * Lorsqu'il y a une liste d'employés, il n'affiche que deux éléments.
 * Il se base sur l'API First pour afficher:
 *
 * @param name      Le nom
 * @param firstname Le prénom
 */
public record EmployeesResponseDTO(
        String name,
        String firstname
) {
}
