package com.example.designnewsfeed.commands;

import com.example.designnewsfeed.controllers.AuthController;
import com.example.designnewsfeed.controllers.UserController;
import com.example.designnewsfeed.dtos.SignupRequest;
import com.example.designnewsfeed.dtos.SignupUserRequestDto;
import org.springframework.stereotype.Component;

@Component
public class SignupCommand implements ExcludeAuthCommand {
    private AuthController authController;


    public SignupCommand(AuthController authController) {
        this.authController = authController;
    }

    @Override
    public boolean canExecute(String input) {
        String[] strs = input.split(" ");
        if(strs.length != 4) return false;
        if(!strs[0].equals(CommandKeywords.signupCommand)) return false;
        return true;
    }

    @Override
    public void execute(String input) {
        String[] strings = input.split(" ");
        SignupRequest signupRequestDto = new SignupRequest();
        signupRequestDto.setName(strings[1]);
        signupRequestDto.setEmail(strings[2]);
        signupRequestDto.setPassword(strings[3]);
        this.authController.registerUser(signupRequestDto);
    }
}
