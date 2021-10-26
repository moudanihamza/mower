package com.mowitnow.mower.domain;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;

import java.util.stream.Stream;

import static org.mockito.Mockito.*;


public class MowerTests {

    private static Position position;

    private static Mower mower;

    @BeforeEach
    void setup() {

        position = mock(Position.class);

        mower = new Mower(position);
    }

    @Test
    @DisplayName("Move operation should call increment position y when orientation is N")
    void moveOperationShouldCallIncrementPositionYWhenOrientationIsN() {

        when(position.getOrientation()).thenReturn(Orientation.N);

        mower.move();

        verify(position, times(1)).incrementY();

    }

    @Test
    @DisplayName("Move operation should call decrement position y when orientation is S")
    void moveOperationShouldCallDecrementPositionYWhenOrientationIsS() {

        when(position.getOrientation()).thenReturn(Orientation.S);

        mower.move();

        verify(position, times(1)).decrementY();

    }

    @Test
    @DisplayName("Move operation should call increment position x when orientation is E")
    void moveOperationShouldCallIncrementPositionYWhenOrientationIsE() {

        when(position.getOrientation()).thenReturn(Orientation.E);
        mower.move();

        verify(position, times(1)).incrementX();

    }

    @Test
    @DisplayName("Move operation should call decrement position x when orientation is W")
    void moveOperationShouldCallDecrementPositionYWhenOrientationIsW() {

        when(position.getOrientation()).thenReturn(Orientation.W);
        mower.move();

        verify(position, times(1)).decrementX();

    }

    @ParameterizedTest
    @DisplayName("right operation should update orientation")
    @MethodSource("provideOrientationsToRight")
    void rightOperationShouldUpdateOrientation(Orientation given, Orientation expected) {

        ArgumentCaptor<Orientation> capturedOrientation = ArgumentCaptor.forClass(Orientation.class);

        when(position.getOrientation()).thenReturn(given);

        mower.right();

        verify(position, times(1)).getOrientation();

        verify(position, times(1)).setOrientation(capturedOrientation.capture());

        Assertions.assertEquals(capturedOrientation.getValue(), expected);
    }


    @ParameterizedTest
    @DisplayName("left operation should update orientation")
    @MethodSource("provideOrientationsToLeft")
    void leftOperationShouldUpdateOrientation(Orientation given, Orientation expected) {

        ArgumentCaptor<Orientation> capturedOrientation = ArgumentCaptor.forClass(Orientation.class);

        when(position.getOrientation()).thenReturn(given);

        mower.left();

        verify(position, times(1)).getOrientation();

        verify(position, times(1)).setOrientation(capturedOrientation.capture());

        Assertions.assertEquals(capturedOrientation.getValue(), expected);
    }

    @Test
    @DisplayName("Mower's toString method should return position's attributes x,y,orientation")
    void toStringShouldReturnPositionAttributes() {

        String expected = "1 1 N";

        when(position.toString()).thenReturn(expected);

        Assertions.assertEquals(mower.toString(), expected);

    }


    private static Stream<Arguments> provideOrientationsToRight() {

        return Stream.of(
                Arguments.of(Orientation.N, Orientation.E),
                Arguments.of(Orientation.E, Orientation.S),
                Arguments.of(Orientation.S, Orientation.W),
                Arguments.of(Orientation.W, Orientation.N)
        );
    }

    private static Stream<Arguments> provideOrientationsToLeft() {

        return Stream.of(
                Arguments.of(Orientation.N, Orientation.W),
                Arguments.of(Orientation.E, Orientation.N),
                Arguments.of(Orientation.S, Orientation.E),
                Arguments.of(Orientation.W, Orientation.S)
        );
    }

}
