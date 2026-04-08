package be.community.Api_First_Enterprise.dto;

/**
 * UpdateEmployeeRequestDTO
 * Permet d'afficher les attributs auquel l'API First de l'employé demande.
 * il demande 4 attributs pour changer les informations de l'employé
 * Dans ce cas-là, il demande:
 *
 * @param name      Le nom de famille de l'employé
 * @param firstname le prénom de l'employé
 * @param service   Le service de l'employé (ex: RH, IT)
 * @param floor     L'étage du service
 */
public record UpdateEmployeeRequestDTO(
        String name,
        String firstname,
        String service,
        Integer floor
) {
}
