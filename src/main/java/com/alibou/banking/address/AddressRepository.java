package com.alibou.banking.address;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<address, Integer> {
    Optional<address> findById(Long id);
    List<address> findAllByCountryAndCity(String country, String city);


}
