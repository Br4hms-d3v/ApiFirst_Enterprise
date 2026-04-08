package be.community.Api_First_Enterprise.controller;

import be.community.Api_First_Enterprise.dto.*;

import be.community.Api_First_Enterprise.service.EmployeeService;
import org.openapitools.api.EmployeeApi;
import org.openapitools.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Le contrôleur des employés
 * - Permet d'exposer l'API qu'il reçoit en HTTP
 * - Premièrement, il appelle le service
 * - Ensuite il convertit les DTO internes en DTO OpenApi
 * - Et enfin, il revoit la réponse
 */
@RestController
public class EmployeeController implements EmployeeApi {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public ResponseEntity<List<Employees>> getEmployees() {
        List<Employees> employList = employeeService.findAll()
                .stream()
                .map(employee -> { // Map each employee to employees Response OAP
                    Employees employeeResponse = new Employees();
                    employeeResponse.setFirstname(employee.firstname());
                    employeeResponse.setName(employee.name());

                    return employeeResponse;
                })
                .toList();

        return ResponseEntity.ok(employList);
    }


    public ResponseEntity<EmployeeResponse> getEmployeeId(Long id) {
        EmployeeResponseDTO employeeById = employeeService.findById(id);
        // Map the response employee From DTO to OpenApi
        EmployeeResponse responseEmployee = new EmployeeResponse();
        responseEmployee.setName(employeeById.name());
        responseEmployee.setFirstname(employeeById.firstname());
        responseEmployee.setService(employeeById.service());
        responseEmployee.setFloor(employeeById.floor());
        // If the address is not null, map address to OpenApi
        if (employeeById.address() != null) {
            Address adrs = new Address();
            adrs.setStreet(employeeById.address().street());
            adrs.setZipcode(employeeById.address().zipcode());
            adrs.setCity(employeeById.address().city());
            responseEmployee.setAddress(adrs);
        }

        return ResponseEntity.ok(responseEmployee); // return a result
    }

    public ResponseEntity<EmployeeResponse> createEmployee(CreateEmployeeRequest createEmployeeRequest) {
        // Create an address -> DTO
        AddressDTO createAddressDto = new AddressDTO(
                createEmployeeRequest.getAddress().getStreet(),
                createEmployeeRequest.getAddress().getZipcode(),
                createEmployeeRequest.getAddress().getCity()
        );
        // Create an employee -> DTO
        CreateEmployeeRequestDTO createEmployeeDto = new CreateEmployeeRequestDTO(
                createEmployeeRequest.getName(),
                createEmployeeRequest.getFirstname(),
                createEmployeeRequest.getService(),
                createEmployeeRequest.getFloor(),
                createAddressDto // add address to employee created
        );

        // Create the employee
        EmployeeResponseDTO newEmployeeResponse = employeeService.createEmployee(createEmployeeDto);

        // Map DTO to Open API (Address)
        Address adrs = new Address();
        adrs.street(newEmployeeResponse.address().street());
        adrs.zipcode(newEmployeeResponse.address().zipcode());
        adrs.city(newEmployeeResponse.address().city());

        // Map DTO to OAPI (Employee)
        EmployeeResponse responseEmployee = new EmployeeResponse();
        responseEmployee.setName(newEmployeeResponse.name());
        responseEmployee.setFirstname(newEmployeeResponse.firstname());
        responseEmployee.setService(newEmployeeResponse.service());
        responseEmployee.setFloor(newEmployeeResponse.floor());

        responseEmployee.setAddress(adrs); // add address to result

        return ResponseEntity.ok(responseEmployee);
    }

    public ResponseEntity<EmployeeWithoutAddressResponse> updateEmployeeId(Long id, UpdateEmployeeRequest updateEmployeeRequest) {

        // DTO to update an employee only
        UpdateEmployeeRequestDTO updateEmployeeDto = new UpdateEmployeeRequestDTO(
                updateEmployeeRequest.getName(),
                updateEmployeeRequest.getFirstname(),
                updateEmployeeRequest.getService(),
                updateEmployeeRequest.getFloor()
        );

        // Call service to update employee
        EmployeeWithoutAddressResponseDTO employeeUpdate = employeeService.updateEmployee(id, updateEmployeeDto);

        // Map DTO to OAPI (employee)
        EmployeeWithoutAddressResponse responseEmployee = new EmployeeWithoutAddressResponse();
        responseEmployee.setName(employeeUpdate.name());
        responseEmployee.setFirstname(employeeUpdate.firstname());
        responseEmployee.setService(employeeUpdate.service());
        responseEmployee.setFloor(employeeUpdate.floor());

        return ResponseEntity.ok(responseEmployee);
    }

    public ResponseEntity<Void> deleteEmployeeById(Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

}
