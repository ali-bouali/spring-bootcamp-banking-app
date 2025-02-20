package com.alibou.banking.address;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public void addAddress(Address address) {
        addressRepository.save(address);
    }

    public void updateAddress(Long addressId, AddressRequest newAddress) {
    Address address=addressRepository.findAddressById(addressId);
        if(address==null) {
            throw new RuntimeException("address not found");
        }
        if(newAddress==null) {
            return ;
        }
        if(newAddress.getPostalCode()!=null) {
            address.setPostalCode(newAddress.getPostalCode());
        }
        if(newAddress.getCity()!=null) {
            address.setCity(newAddress.getCity());
        }
        if(newAddress.getCountry()!=null) {
            address.setCountry(newAddress.getCountry());
        }
        addressRepository.save(address);

    }
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public List<Address>getCustomAddress(AddressRequest addressRequestSearch){
        return addressRepository.findAddresses(addressRequestSearch.getCountry(), addressRequestSearch.getStreet(), addressRequestSearch.getCity(), addressRequestSearch.getPostalCode());
    }

}
