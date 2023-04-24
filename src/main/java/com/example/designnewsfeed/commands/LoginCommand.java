package com.example.designnewsfeed.commands;

import com.example.designnewsfeed.controllers.AuthController;
import com.example.designnewsfeed.dtos.LoginRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.stereotype.Component;

@Component
public class LoginCommand implements Command{
    private AuthController authController;

    public LoginCommand(AuthController authController) {
        this.authController = authController;
    }

    @Override
    public boolean canExecute(String input, MockHttpSession session) {
        String[] strs = input.split(" ");
        if(strs.length != 3) return false;
        if(!strs[0].equals(CommandKeywords.loginCommand)) return false;
        return true;
    }

    @Override
    public void execute(String input, MockHttpSession session) {

        String[] strs = input.split(" ");
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(strs[1]);
        loginRequest.setPassword(strs[2]);
        boolean isValid = authController.authenticateUser(loginRequest);
        if(!isValid) return;

        // after login settingup session
        session.setAttribute("email", strs[1]);
        System.out.println("Session set for Email: " + strs[1]);
    }
}
