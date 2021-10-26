package com.mowitnow.mower.application;

import com.mowitnow.mower.ApplicationProperties;
import com.mowitnow.mower.domain.*;
import com.mowitnow.mower.utils.Tuple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MowerExecutorImplTests {

    @Test
    void startMethodShouldExecuteMowers() {

        final ApplicationProperties applicationProperties = mock(ApplicationProperties.class);

        final MowerRepository mowerRepository = mock(MowerRepository.class);

        List<MowerData> mowers = this.getMowers();

        when(mowerRepository.getMowers()).thenReturn(mowers);

        final MowerExecutorImpl mowerExecutor = new MowerExecutorImpl(applicationProperties, mowerRepository);

        mowerExecutor.start();

        Assertions.assertIterableEquals(mowers.stream().map(o -> o.getMower().toString()).collect(Collectors.toList()), Arrays.asList("1 3 N", "5 1 E"));

    }

    private List<MowerData> getMowers() {

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
