package com.mowitnow.mower.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommandTests {

    @Test
    void commandListTestShouldBeSuccessful() {
        Assertions.assertArrayEquals(Command.values(), new Command[]{Command.D, Command.G, Command.A});
    }
}
