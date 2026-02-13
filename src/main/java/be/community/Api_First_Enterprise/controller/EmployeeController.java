package be.community.Api_First_Enterprise.controller;

import be.community.Api_First_Enterprise.dto.CreateEmployeeRequestDTO;
import be.community.Api_First_Enterprise.dto.EmployeeResponseDTO;
import be.community.Api_First_Enterprise.dto.UpdateEmployeeRequestDTO;
import be.community.Api_First_Enterprise.mapper.ApiEmployeeMapper;
import be.community.Api_First_Enterprise.service.EmployeeService;
import jakarta.validation.Valid;
import org.example.bankingapi.api.EmployeeApi;
import org.example.bankingapi.model.CreateEmployeeRequest;
import org.example.bankingapi.model.EmployeeResponse;
import org.example.bankingapi.model.EmployeesResponse;
import org.example.bankingapi.model.UpdateEmployeeRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController implements EmployeeApi {

    private final EmployeeService employeeService;
    private final ApiEmployeeMapper apiEmployeeMapper;

    public EmployeeController(EmployeeService employeeService, ApiEmployeeMapper apiEmployeeMapper) {
        this.employeeService = employeeService;
        this.apiEmployeeMapper = apiEmployeeMapper;
    }

    public ResponseEntity<List<EmployeesResponse>> getEmployees() {
        var employeeList = employeeService
                .getEmployees()
                .stream()
                .map(apiEmployeeMapper::employeesToApi)
                .toList();
        return ResponseEntity.ok(employeeList);
    }

    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
        var employee = employeeService.getEmployee(id);
        EmployeeResponse employeeToApi = apiEmployeeMapper.employeeToApi(employee);
        return ResponseEntity.ok(employeeToApi);
    }

    public ResponseEntity<EmployeeResponse> createEmployee(CreateEmployeeRequest createEmployeeRequest) {
        var createEmployeeRequestDTO = apiEmployeeMapper.CreateToDto(createEmployeeRequest);
        EmployeeResponseDTO newEmployee = employeeService.createEmployee(createEmployeeRequestDTO);
        EmployeeResponse employeeToApi = apiEmployeeMapper.employeeToApi(newEmployee);
        return ResponseEntity.ok(employeeToApi);
    }

    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id, UpdateEmployeeRequest updateEmployeeRequest) {
        var updateEmployeeRequestDTO = apiEmployeeMapper.UpdateToDto(id, updateEmployeeRequest);
        EmployeeResponseDTO updateEmployee = employeeService.updateEmployee(id, updateEmployeeRequestDTO);
        EmployeeResponse employeeToApi = apiEmployeeMapper.employeeToApi(updateEmployee);
        return ResponseEntity.ok(employeeToApi);
    }

    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
