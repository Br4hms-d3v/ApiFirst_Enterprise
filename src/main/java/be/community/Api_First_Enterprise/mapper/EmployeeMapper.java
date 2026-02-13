package be.community.Api_First_Enterprise.mapper;

import be.community.Api_First_Enterprise.dto.CreateEmployeeRequestDTO;

import be.community.Api_First_Enterprise.dto.EmployeeResponseDTO;
import be.community.Api_First_Enterprise.dto.EmployeesResponseDTO;
import be.community.Api_First_Enterprise.dto.UpdateEmployeeRequestDTO;
import be.community.Api_First_Enterprise.entities.Employee;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring") // Generate from his interface and this implementation must be a Bean spring
public interface EmployeeMapper {
    // Entity to DTO
    EmployeesResponseDTO toEmployeesResponse(Employee employee);

    EmployeeResponseDTO toEmployeeResponse(Employee employee);

    CreateEmployeeRequestDTO toCreateEmployeeRequest(Employee employee);

    // DTO to Entity
    Employee toEntity(CreateEmployeeRequestDTO createEmployeeRequestDto);

    /**
     * Tells to MapStruct that for this method
     * it must not overwrite existing fields with null values
     * from DTO UpdateEmployeeRequest
     *
     * @param updateEmployeeRequestDto update the employee DTO
     * @param employee              entity employee
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEmployee(UpdateEmployeeRequestDTO updateEmployeeRequestDto, @MappingTarget Employee employee);

}
