package com.dimensiondata.console;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataInputStream;
import java.io.IOException;

public class SystemConsoleInteractor implements ConsoleInteractor {

    @Override
    public void printError(String s) {
        System.err.println(s);
    }

    @Override
    public void printError(Object o) {
        System.err.println(o);
    }

    @Override
    public void printNewLine(String s) {
        System.out.println(s);
    }

    @Override
    public void printNewLine(Object o) {
        try {
            printNewLine(new ObjectMapper().writeValueAsString(o));
        } catch (JsonProcessingException e) {
            printError("jackson cannot write object to string");
        }
    }

    @Override
    public String readLine() {
        DataInputStream in = new DataInputStream(System.in);
        try {
            return in.readLine();
        } catch (IOException e) {
            throw new ConsoleInteractorException(e);
        }
    }
}
