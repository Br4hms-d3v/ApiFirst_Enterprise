package be.community.Api_First_Enterprise.mapper;

import be.community.Api_First_Enterprise.dto.CreateEmployeeRequestDTO;
import be.community.Api_First_Enterprise.dto.EmployeeResponseDTO;
import be.community.Api_First_Enterprise.dto.EmployeesResponseDTO;
import be.community.Api_First_Enterprise.dto.UpdateEmployeeRequestDTO;
import org.example.bankingapi.model.CreateEmployeeRequest;
import org.example.bankingapi.model.EmployeeResponse;
import org.example.bankingapi.model.EmployeesResponse;
import org.example.bankingapi.model.UpdateEmployeeRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApiEmployeeMapper {

    // API to DTO
    EmployeeResponse employeeToApi(EmployeeResponseDTO employeeResponseDto);

    EmployeesResponse employeesToApi(EmployeesResponseDTO employeesResponseDto);

    CreateEmployeeRequestDTO CreateToDto(CreateEmployeeRequest createEmployeeRequestDto);

    UpdateEmployeeRequestDTO UpdateToDto(Long id, UpdateEmployeeRequest updateEmployeeRequestDto);

}
