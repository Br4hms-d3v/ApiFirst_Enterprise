package be.community.Api_First_Enterprise.dto;

/**
 * EmployeeResponseDto
 * Affiche les attributs concernant ce que l'employé ID
 * C'est la réponse qui est demandée.
 * il demande 4 choses:
 *
 * @param name      le nom de l'employé
 * @param firstname le prénom de l'employé
 * @param service   le service de l'employé
 * @param floor     l'étage du service
 */
public record EmployeeWithoutAddressResponseDTO(
        String name,
        String firstname,
        String service,
        Integer floor
) {
}
