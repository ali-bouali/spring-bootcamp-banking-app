package com.alibou.banking.address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository  extends JpaRepository<Address, Long> {

    List<Address>findAddressesByCountry(String country);

    List<Address>findAddressesByCity(String city);

    Address findAddressById(Long id);

    @Query("""
            SELECT a FROM Address a WHERE  
            (:country IS NULL OR a.country LIKE :country) AND  
            (:street IS NULL OR a.street LIKE %:street%) AND  
            (:postalCode IS NULL OR a.postalCode = :postalCode) AND  
            (:city IS NULL OR a.city LIKE %:city%)""")
    List<Address> findAddresses(
            @Param("country") String country,
            @Param("street") String street,
            @Param("postalCode") String postalCode,
            @Param("city") String city
    );
}