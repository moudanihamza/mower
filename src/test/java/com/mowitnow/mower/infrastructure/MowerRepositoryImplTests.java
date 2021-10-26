package com.mowitnow.mower.infrastructure;

import com.mowitnow.mower.ApplicationProperties;
import com.mowitnow.mower.domain.*;
import com.mowitnow.mower.utils.Tuple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class MowerRepositoryImplTests {

    @Test
    void getMowerMethodShouldReturnTheCorrectValues() {
        ApplicationProperties applicationProperties = mock(ApplicationProperties.class);

        MowerRepositoryImpl mowerRepository = new MowerRepositoryImpl(applicationProperties);

        when(applicationProperties.getElementsSeparator()).thenReturn("\\s+");

        when(applicationProperties.getConfigPath()).thenReturn("files/config.txt");

        when(applicationProperties.getMinY()).thenReturn(0);

        when(applicationProperties.getMinX()).thenReturn(0);

        List<MowerData> mowersData = mowerRepository.getMowers();

        Assertions.assertEquals(mowersData, this.getExpectedMowersData());

        verify(applicationProperties, times(3)).getElementsSeparator();

        verify(applicationProperties, times(1)).getConfigPath();

        verify(applicationProperties, atLeast(1)).getMinY();

        verify(applicationProperties, atLeast(1)).getMinX();
    }

    private List<MowerData> getExpectedMowersData() {

        Position position1 = Position.builder()
                .point(new Tuple<>(1, 2))
                .minPoint(new Tuple<>(0, 0))
                .maxPoint(new Tuple<>(5, 5))
                .orientation(Orientation.N)
                .build();

        Position position2 = Position.builder()
                .point(new Tuple<>(3, 3))
                .minPoint(new Tuple<>(0, 0))
                .maxPoint(new Tuple<>(5, 5))
                .orientation(Orientation.E)
                .build();

        List<Command> commands1 = Arrays.asList(Command.G, Command.A, Command.G, Command.A, Command.G, Command.A, Command.G, Command.A, Command.A);

        List<Command> commands2 = Arrays.asList(Command.A, Command.A, Command.D, Command.A, Command.A, Command.D, Command.A, Command.D, Command.D, Command.A);

        return Arrays.asList(new MowerData(new Mower(position1), commands1), new MowerData(new Mower(position2), commands2));
    }
}
