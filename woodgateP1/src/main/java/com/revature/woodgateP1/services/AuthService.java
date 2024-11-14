package com.revature.woodgateP1.services;

import com.revature.woodgateP1.controllers.AuthController;
import com.revature.woodgateP1.daos.AuthDAO;
import com.revature.woodgateP1.models.DTOs.LoginDTO;
import com.revature.woodgateP1.models.DTOs.OutgoingUserDTO;
import com.revature.woodgateP1.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private AuthDAO aDAO;

    @Autowired
    public AuthService(AuthDAO aDAO) {
        this.aDAO = aDAO;
    }

    public OutgoingUserDTO login(LoginDTO lDTO, HttpSession session) {
        User u = aDAO.findByUsernameAndPassword(lDTO.getUsername(), lDTO.getPassword());
        if(u == null){
            //TODO: we could have made a custom LoginFailed Exception, but no time
            throw new IllegalArgumentException("No user found with those credentials!");
        }

        //if user found, login successful; "session lives on controller layer"
        AuthController.session = session;

        //Store the User info in the session
        AuthController.session.setAttribute("userId", u.getUserId());
        AuthController.session.setAttribute("firstname", u.getFirstname());
        AuthController.session.setAttribute("lastname", u.getLastname());
        AuthController.session.setAttribute("username", u.getUsername());
        AuthController.session.setAttribute("role", u.getRole());

        //Process the User into an OutgoingUserDTO and return it!
        return new OutgoingUserDTO(u.getUserId(), u.getFirstname(), u.getLastname(), u.getUsername(), u.getRole());
    }
}
