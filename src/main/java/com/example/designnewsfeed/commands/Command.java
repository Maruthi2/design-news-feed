package com.example.designnewsfeed.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.mock.web.MockHttpSession;

public interface Command {
    boolean canExecute(String input,MockHttpSession session);
    void execute(String input,MockHttpSession session) throws JsonProcessingException;
}
