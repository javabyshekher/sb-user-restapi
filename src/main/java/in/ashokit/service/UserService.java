package in.ashokit.service;

import in.ashokit.entity.UserEntity;
import in.ashokit.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public UserEntity saveUser(UserEntity userEntity) {
        UserEntity savedUserEntity = userRepo.save(userEntity);
        return savedUserEntity;
    }

    public UserEntity updateUser(Integer userId, UserEntity userEntity) {
        Optional<UserEntity> byId = userRepo.findById(userId);
        if (byId.isPresent()) {
            userEntity.setUserId(userId);
            UserEntity updated = userRepo.save(userEntity);
            return updated;
        }
        else
            return null;
    }

    public UserEntity deleteUser(Integer userId) {
        Optional<UserEntity> byId = userRepo.findById(userId);
        if (byId.isPresent()) {
            userRepo.deleteById(userId);
            return byId.get();
        }
        else
            return null;
    }

    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    public UserEntity  getUserById(Integer userId) {
        return userRepo.findById(userId).orElse(null);
    }

    public UserEntity getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }

}
