package com.dimensiondata;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CommandTest {

    @Test
    public void shouldReturnNotExist() throws Exception {
        assertThat(Command.getCommand("?"), is(Command.NOT_EXIST));
    }

    @Test
    public void shouldReturnHelpCommand() throws Exception {
        assertThat(Command.getCommand("help"), is(Command.HELP));
    }

    @Test
    public void shouldReturnCountServersCommand() throws Exception {
        assertThat(Command.getCommand("countServers"), is(Command.COUNT_SERVERS));
    }
}
