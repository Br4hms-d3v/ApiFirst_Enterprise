package be.community.Api_First_Enterprise.repository;

import be.community.Api_First_Enterprise.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
