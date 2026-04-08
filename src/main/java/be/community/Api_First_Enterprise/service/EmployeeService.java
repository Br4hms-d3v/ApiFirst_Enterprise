package be.community.Api_First_Enterprise.service;


import be.community.Api_First_Enterprise.dto.*;
import be.community.Api_First_Enterprise.entity.Address;
import be.community.Api_First_Enterprise.entity.Employee;
import be.community.Api_First_Enterprise.repository.AddressRepository;
import be.community.Api_First_Enterprise.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Le service employé.
 * Il communique avec les DTO, repository tout en se basant sur le modèle entité (règle métier)
 * Chaque méthode permet de contenir la logique métier
 */
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;

    public EmployeeService(EmployeeRepository employeeRepository, AddressRepository addressRepository) {
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
    }

    public List<EmployeesResponseDTO> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(employee -> new EmployeesResponseDTO(
                        employee.getName(),
                        employee.getFirstname()
                ))
                .toList();
    }

    @Transactional()
    public EmployeeResponseDTO findById(Long id) {
        Employee employeeById = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));

        // return a response employee with his address
        return new EmployeeResponseDTO(
                employeeById.getName(),
                employeeById.getFirstname(),
                employeeById.getService(),
                employeeById.getFloor(),
                new AddressDTO(
                        employeeById.getAddress().getStreet(),
                        employeeById.getAddress().getZipcode(),
                        employeeById.getAddress().getCity()
                )
        );
    }

    @Transactional
    public EmployeeResponseDTO createEmployee(CreateEmployeeRequestDTO createEmployeeDto) {
        Employee newEmployee = new Employee();

//        if (createEmployeeDto.name().isBlank()) {
//            throw new EmployeeNameMustNotEmptyException("The name must not empty");
//        }
        newEmployee.setName(createEmployeeDto.name());
        newEmployee.setFirstname(createEmployeeDto.firstname());
        newEmployee.setService(createEmployeeDto.service());
        newEmployee.setFloor(createEmployeeDto.floor());

        Address newAddress = new Address();
        newAddress.setStreet(createEmployeeDto.address().street());
        newAddress.setCity(createEmployeeDto.address().city());
        newAddress.setZipcode(createEmployeeDto.address().zipcode());

        // save address before
        addressRepository.save(newAddress);

        AddressDTO responseAddressDTO = new AddressDTO(
                newAddress.getStreet(),
                newAddress.getZipcode(),
                newAddress.getCity()
        );

        // then save the employee
        newEmployee.setAddress(newAddress);
        employeeRepository.save(newEmployee);

        // return a responses employee with his address
        return new EmployeeResponseDTO(
                newEmployee.getName(),
                newEmployee.getFirstname(),
                newEmployee.getService(),
                newEmployee.getFloor(),
                responseAddressDTO
        );
    }

    public EmployeeWithoutAddressResponseDTO updateEmployee(Long id, UpdateEmployeeRequestDTO updateEmployeeDto) {
        Employee employeeUpdate = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));

        employeeUpdate.setName(updateEmployeeDto.name());
        employeeUpdate.setFirstname(updateEmployeeDto.firstname());
        employeeUpdate.setService(updateEmployeeDto.service());
        employeeUpdate.setFloor(updateEmployeeDto.floor());

        Employee newEmployeeUpdated = employeeRepository.save(employeeUpdate);

        return new EmployeeWithoutAddressResponseDTO(
                newEmployeeUpdated.getName(),
                newEmployeeUpdated.getFirstname(),
                newEmployeeUpdated.getService(),
                newEmployeeUpdated.getFloor()
        );
    }

    @Transactional
    public void deleteEmployee(Long id) {
        Employee employeeDelete = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        employeeRepository.deleteById(employeeDelete.getId());
    }
}
