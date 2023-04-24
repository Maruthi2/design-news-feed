package com.example.designnewsfeed.commands;



public interface ExcludeAuthCommand {
    boolean canExecute(String input);
    void execute(String input);
}
