package com.example.designnewsfeed.commands;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExcludeAuthCommandRegistry {
    public static List<ExcludeAuthCommand> commands = new ArrayList<>();

    public void register(ExcludeAuthCommand command){
        if(!commands.contains(command)){
            commands.add(command);
        }
    }

    public void execute(String input){
        for (ExcludeAuthCommand command:commands){
            if(command.canExecute(input)){
                command.execute(input);
            }
        }
    }
}
