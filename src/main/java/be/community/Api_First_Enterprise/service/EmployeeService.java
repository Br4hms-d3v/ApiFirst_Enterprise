package be.community.Api_First_Enterprise.service;

import be.community.Api_First_Enterprise.dto.CreateEmployeeRequestDTO;
import be.community.Api_First_Enterprise.dto.EmployeeResponseDTO;
import be.community.Api_First_Enterprise.dto.EmployeesResponseDTO;
import be.community.Api_First_Enterprise.dto.UpdateEmployeeRequestDTO;

import java.util.List;

public interface EmployeeService {

    List<EmployeesResponseDTO> getEmployees();

    EmployeeResponseDTO getEmployee(Long id);

    EmployeeResponseDTO createEmployee(CreateEmployeeRequestDTO createEmployeeRequestDto);

    EmployeeResponseDTO updateEmployee(Long id, UpdateEmployeeRequestDTO updateEmployeeRequestDto);

    void deleteEmployee(Long id);

}
