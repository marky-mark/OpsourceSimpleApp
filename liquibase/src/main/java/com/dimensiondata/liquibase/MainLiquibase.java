package com.dimensiondata.liquibase;

import liquibase.exception.CommandLineParsingException;

import java.io.IOException;

public final class MainLiquibase {

    private MainLiquibase() { }

    public static void main(String[] args) throws CommandLineParsingException, IOException {
        liquibase.integration.commandline.Main.main(args);
    }
}
