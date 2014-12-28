package com.dimensiondata.console;

public interface ConsoleInteractor {
    void printError(String s);
    void printError(Object o);
    void printNewLine(String s);
    void printNewLine(Object o);
    String readLine();
}
