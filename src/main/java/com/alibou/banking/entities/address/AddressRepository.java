package com.alibou.banking.entities.address;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    Optional<AddressEntity> findByCityAndState(String city, String state);

    @Transactional
    @Modifying
    @Query("UPDATE AddressEntity a SET a.city = :newCity WHERE a.city = :oldCity")
    int updateCity(String oldCity,String newCity);
}
