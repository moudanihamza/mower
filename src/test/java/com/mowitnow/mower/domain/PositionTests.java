package com.mowitnow.mower.domain;

import com.mowitnow.mower.utils.Tuple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTests {

    @Test
    @DisplayName("Position should Increment x")
    void positionShouldIncrementX() {
        Position position = Position.builder()
                                    .point(new Tuple<>(1, 2))
                                    .maxPoint(new Tuple<>(2, 2))
                                    .build();

        position.incrementX();

        Assertions.assertEquals(position.getPoint().getLeft(), 2);

        Assertions.assertEquals(position.getMaxPoint().getLeft(), 2);
    }

    @Test
    @DisplayName("Position should Increment Y")
    void positionShouldIncrementY() {

        Position position = Position.builder()
                                    .point(new Tuple<>(1, 1))
                                    .maxPoint(new Tuple<>(1, 2))
                                    .build();

        position.incrementY();

        Assertions.assertEquals(position.getPoint().getRight(), 2);

        Assertions.assertEquals(position.getMaxPoint().getRight(), 2);
    }

    @Test
    @DisplayName("Position should Decrement X")
    void positionShouldDecrementX() {

        Position position = Position.builder()
                                    .point(new Tuple<>(3, 1))
                                    .minPoint(new Tuple<>(2, 1))
                                    .build();

        position.decrementX();

        Assertions.assertEquals(position.getPoint().getLeft(), 2);

        Assertions.assertEquals(position.getMinPoint().getLeft(), 2);
    }

    @Test
    @DisplayName("Position should Decrement Y")
    void positionShouldDecrementY() {

        Position position = Position.builder()
                                    .point(new Tuple<>(1, 3))
                                    .minPoint(new Tuple<>(2, 2))
                                    .build();

        position.decrementY();

        Assertions.assertEquals(position.getPoint().getRight(), 2);

        Assertions.assertEquals(position.getMinPoint().getRight(), 2);
    }

    @Test
    @DisplayName("Position' toString method should return fields (x,y,orientation) concatenated with spaces")
    void toStringShouldReturnAllAttributes() {

        Position position = Position.builder()
                                    .point(new Tuple<>(1, 1))
                                    .orientation(Orientation.N)
                                    .build();

        Assertions.assertEquals(position.toString(), "1 1 N");
    }

    /********************************          Unhappy paths        *********************************/

    @Test
    @DisplayName("Position should not Increment x")
    void positionShouldNotIncrementX() {

        Position position = Position.builder()
                                    .point(new Tuple<>(1, 3))
                                    .maxPoint(new Tuple<>(1, 3))
                                    .build();

        position.incrementX();

        Assertions.assertEquals(position.getPoint().getLeft(), 1);

        Assertions.assertEquals(position.getPoint().getLeft(), 1);
    }

    @Test
    @DisplayName("Position should not Increment Y")
    void positionShouldNotIncrementY() {

        Position position = Position.builder()
                                    .point(new Tuple<>(1, 1))
                                    .maxPoint(new Tuple<>(1, 1))
                                    .build();

        position.incrementY();

        Assertions.assertEquals(position.getPoint().getRight(), 1);
        Assertions.assertEquals(position.getMaxPoint().getRight(), 1);
    }

    @Test
    @DisplayName("Position should not Decrement X")
    void positionShouldNotDecrementX() {

        Position position = Position.builder()
                                    .point(new Tuple<>(1, 1))
                                    .minPoint(new Tuple<>(1, 1))
                                    .build();

        position.decrementX();

        Assertions.assertEquals(position.getPoint().getLeft(), 1);

        Assertions.assertEquals(position.getMinPoint().getLeft(), 1);
    }

    @Test
    @DisplayName("Position should not Decrement Y")
    void positionShouldNotDecrementY() {
        Position position = Position.builder()
                .point(new Tuple<>(1, 1))
                .minPoint(new Tuple<>(1, 1))
                .build();

        position.decrementY();

        Assertions.assertEquals(position.getPoint().getRight(), 1);
        Assertions.assertEquals(position.getMinPoint().getRight(), 1);
    }

    @Test
    @DisplayName("Position Equals should be successful")
    void positionEqualsShouldBeSuccessful() {
        Position position1 = Position.builder()
                .point(new Tuple<>(1, 1))
                .minPoint(new Tuple<>(1, 1))
                .build();

        Position position2 = Position.builder()
                .point(new Tuple<>(1, 1))
                .minPoint(new Tuple<>(1, 1))
                .build();

        Assertions.assertEquals(position1,position2);
    }
}
