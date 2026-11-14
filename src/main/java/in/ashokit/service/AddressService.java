package in.ashokit.service;

import in.ashokit.entity.AddressEntity;
import in.ashokit.entity.UserEntity;
import in.ashokit.repository.AddressRepository;
import in.ashokit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addrRepo;

    @Autowired
    private UserRepository userRepo;

    public AddressEntity saveAddress(Integer userId, AddressEntity addressEntity) {

        Optional<UserEntity> byId = userRepo.findById(userId);
        if(byId.isPresent()) {
            UserEntity userEntity = byId.get();
            addressEntity.setUser(userEntity);
            AddressEntity savedAddressEntity = addrRepo.save(addressEntity);
            return savedAddressEntity;
        }
        return null;
    }

    public AddressEntity updateAddress(Integer addrId, AddressEntity addressEntity) {
        Optional<AddressEntity> byId = addrRepo.findById(addrId);
        if (byId.isPresent()) {
            AddressEntity updatedAddress = addrRepo.save(addressEntity);
            return updatedAddress;
        }
        return null;
    }

    public AddressEntity deleteAddress(Integer addrId) {
        Optional<AddressEntity> byId = addrRepo.findById(addrId);
        if (byId.isPresent()) {
            addrRepo.deleteById(addrId);
            return byId.get();
        }
        return null;
    }

    public List<AddressEntity> getAddressesByUserId(Integer userId) {
        return addrRepo.findByUserUserId(userId);
    }

    public AddressEntity getAddressById(Integer addrId) {
        return addrRepo.findById(addrId).orElse(null);
    }

}
