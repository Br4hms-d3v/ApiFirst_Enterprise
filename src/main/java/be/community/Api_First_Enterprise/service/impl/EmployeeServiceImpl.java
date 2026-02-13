package be.community.Api_First_Enterprise.service.impl;

import be.community.Api_First_Enterprise.dto.CreateEmployeeRequestDTO;
import be.community.Api_First_Enterprise.dto.EmployeeResponseDTO;
import be.community.Api_First_Enterprise.dto.EmployeesResponseDTO;
import be.community.Api_First_Enterprise.dto.UpdateEmployeeRequestDTO;
import be.community.Api_First_Enterprise.entities.Employee;
import be.community.Api_First_Enterprise.exception.EmployeeNotFoundException;
import be.community.Api_First_Enterprise.mapper.EmployeeMapper;
import be.community.Api_First_Enterprise.repository.EmployeeRepository;
import be.community.Api_First_Enterprise.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<EmployeesResponseDTO> getEmployees() {
        return employeeRepository
                .findAll()
                .stream()
                .map(employeeMapper::toEmployeesResponse)
                .toList();
    }

    @Override
    public EmployeeResponseDTO getEmployee(Long id) {
        return employeeRepository
                .findById(id)
                .map(employeeMapper::toEmployeeResponse)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public EmployeeResponseDTO createEmployee(CreateEmployeeRequestDTO createEmployeeRequestDto) {
        Employee employee = employeeMapper.toEntity(createEmployeeRequestDto);
        Employee createEmployee = employeeRepository.save(employee);
        return employeeMapper.toEmployeeResponse(createEmployee);
    }

    @Override
    public EmployeeResponseDTO updateEmployee(Long id, UpdateEmployeeRequestDTO updateEmployeeRequestDto) {
        Employee employeeId = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        employeeMapper.updateEmployee(updateEmployeeRequestDto, employeeId);
        Employee updateEmployee = employeeRepository.save(employeeId);
        return employeeMapper.toEmployeeResponse(updateEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        }else{
            throw new EmployeeNotFoundException(id);
        }
    }
}
