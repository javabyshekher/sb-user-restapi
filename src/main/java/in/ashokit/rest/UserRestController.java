package in.ashokit.rest;

import in.ashokit.entity.UserEntity;
import in.ashokit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
        UserEntity savedUser = userService.saveUser(userEntity);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Integer userId, @RequestBody UserEntity userEntity) {
        UserEntity updatedUser = userService.updateUser(userId, userEntity);
        if(updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<UserEntity> deleteUser(@PathVariable Integer userId) {
        UserEntity deletedUser = userService.deleteUser(userId);
        if(deletedUser != null) {
            return new ResponseEntity<>(deletedUser, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/byId/{userId}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Integer userId) {
        UserEntity userById = userService.getUserById(userId);
        if(userById != null) {
            return new ResponseEntity<>(userById, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/by-email/{email}")
    public ResponseEntity<UserEntity> getUserByEmail(@PathVariable String email) {
        UserEntity userByEmail = userService.getUserByEmail(email);
        if(userByEmail != null) {
            return new ResponseEntity<>(userByEmail, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
