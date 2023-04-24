package com.example.designnewsfeed.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandRegistry {
    public static List<Command> commands = new ArrayList<>();

    public void register(Command command){
        if(!commands.contains(command)){
            commands.add(command);
        }
    }

    public void execute(String input, MockHttpSession session) throws JsonProcessingException {

        for (Command command:commands){
            if(command.canExecute(input,session)){
                command.execute(input,session);
            }
        }
    }

}
