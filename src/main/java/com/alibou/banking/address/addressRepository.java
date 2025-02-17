package com.alibou.banking.address;

import com.alibou.banking.user.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface addressRepository extends JpaRepository<address,Long>{

    List<address> findByCity(String city);

    List<address> findByState(String state);

    List<address> findByPostalCode(String postalCode);

    List<address> findByCountry(String country);

    List<address> findByCityAndState(String city, String state);

    boolean existsByPostalCode(String postalCode);


}
