package com.revature.woodgateP1.controllers;

import com.revature.woodgateP1.models.DTOs.LoginDTO;
import com.revature.woodgateP1.models.DTOs.OutgoingUserDTO;
import com.revature.woodgateP1.services.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    //filled on successful login
    public static HttpSession session;

    @PostMapping
    public ResponseEntity<OutgoingUserDTO> login(@RequestBody LoginDTO lDTO, HttpSession session){
        //send LoginDTO to service, getting us the OutUser
        OutgoingUserDTO uDTO = authService.login(lDTO, session);

        //The session gets initialized and filled with user data in the service layer!
        //if we get here, login was successful and session was created!
        return ResponseEntity.ok(uDTO);
    }

    //Exception Handler (stole this from the UserController)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e){
        //Return a 400 (BAD REQUEST) status code with the exception message
        return ResponseEntity.status(400).body(e.getMessage());
    }
}
