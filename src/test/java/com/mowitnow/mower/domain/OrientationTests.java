package com.mowitnow.mower.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrientationTests {


    @Test
    @DisplayName("Next orientation tests should be successful")
    void nextOrientationsShouldBeSuccessful() {

        Assertions.assertEquals(Orientation.N.next(), Orientation.E);

        Assertions.assertEquals(Orientation.E.next(), Orientation.S);

        Assertions.assertEquals(Orientation.S.next(), Orientation.W);

        Assertions.assertEquals(Orientation.W.next(), Orientation.N);
    }

    @Test
    @DisplayName("Previous orientation tests should be successful")
    void previousOrientationsShouldBeSuccessful() {

        Assertions.assertEquals(Orientation.N.previous(), Orientation.W);

        Assertions.assertEquals(Orientation.E.previous(), Orientation.N);

        Assertions.assertEquals(Orientation.S.previous(), Orientation.E);

        Assertions.assertEquals(Orientation.W.previous(), Orientation.S);
    }

}
