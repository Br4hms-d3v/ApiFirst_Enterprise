package be.community.Api_First_Enterprise.repository;

import be.community.Api_First_Enterprise.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
