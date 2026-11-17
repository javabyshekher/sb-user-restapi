package in.ashokit.rest;

import in.ashokit.entity.UserEntity;
import in.ashokit.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="users", description = "User API endpoints")
@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Creates a New User", description = "It adds a New User to a Database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "The server has returned the response" ),
            @ApiResponse(responseCode = "201", description = "The user has been created successfully")
    })
    @PostMapping("/create")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
        UserEntity savedUser = userService.saveUser(userEntity);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserEntity> updateUser(@Parameter(name="userId", description = "Id of a user to update")@PathVariable Integer userId, @RequestBody UserEntity userEntity) {
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
