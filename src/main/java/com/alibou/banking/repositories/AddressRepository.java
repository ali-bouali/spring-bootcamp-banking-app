package com.alibou.banking.repositories;

import com.alibou.banking.entites.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
