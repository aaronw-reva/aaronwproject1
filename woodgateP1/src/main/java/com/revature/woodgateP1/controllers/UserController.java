package com.revature.woodgateP1.controllers;

import com.revature.woodgateP1.aspects.AdminOnly;
import com.revature.woodgateP1.models.DTOs.OutgoingUserDTO;
import com.revature.woodgateP1.models.User;
import com.revature.woodgateP1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //POST request to insert a new User
    @PostMapping //POST requests to /users will come here
    public ResponseEntity<User> registerUser(@RequestBody User newUser){
        //@RequestBody tells Spring to convert the JSON in the Request to a User object

        //Send the new user to the Service to be inserted, and save the returned User
        User u = userService.register(newUser);

        //Return the saved User with a 201 status code (201 - CREATED)
        return ResponseEntity.status(201).body(u);
    }

    @GetMapping //GET requests to /users will come here
    public ResponseEntity<List<OutgoingUserDTO>> getAllUsers(){

        //not much error handling in a get all
        List<OutgoingUserDTO> allUsers = userService.getAllUsers();

        //send the users back with a 200 status code
        return ResponseEntity.ok(allUsers);

    }

    //GET requests to get a single User by username
    @GetMapping("/{username}") //GET requests to /users/{username} will come here
    public ResponseEntity<?> getUserByUsername(@PathVariable String username){

        //ResponseEntity<?>??? what's that?
        //It lets us send any data type we want in the response
        //I avoid this when possible, it can make debugs pretty annoying
        //But I'll often use it since it's so flexible

        //if no user is found, we can send a message saying no user found
        if(userService.getUserByUsername(username) == null){
            return ResponseEntity.status(404).body("No user found with username: " + username);
        }

        //Return the found User with a 200 status code
        return ResponseEntity.ok(userService.getUserByUsername(username));

    }

    //A method that updates a User's role
    @AdminOnly //ONLY ADMINS CAN DO THIS (See the AuthAspect)
    @PatchMapping("/{userId}") //PATCH requests to /users/{userId} will come here
    public ResponseEntity<User> updateUserRole(@PathVariable int userId){

        //send back a 202 (ACCEPTED) with the User returned from the Service as the Response Body
        return ResponseEntity.status(202).body(userService.updateUserRole(userId, "manager"));

    }

    //A method that deletes a User by id
    @AdminOnly //ONLY ADMINS CAN DO THIS (See the AuthAspect)
    @DeleteMapping("/{userId}") //DELETE requests to /users/{userId} will come here
    public ResponseEntity<User> deleteUserById(@PathVariable int userId){

        return ResponseEntity.ok(userService.deleteUserById(userId));

    }

    //Exception Handlers----------------------------------------

    //Exception Handler for IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e){
        //Return a 400 (BAD REQUEST) status code with the exception message
        return ResponseEntity.status(400).body(e.getMessage());
    }

    //TODO: Handler for SqlExceptions!!
}
