package com.mowitnow.mower.infrastructure;

import com.mowitnow.mower.ApplicationProperties;
import com.mowitnow.mower.domain.*;
import com.mowitnow.mower.utils.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MowerRepositoryImpl implements MowerRepository {

    private final ApplicationProperties applicationProperties;

    private List<MowerData> mowersData;

    @Override
    public List<MowerData> getMowers() {

        if (Objects.isNull(mowersData)) {

            List<String> lines = this.getFileLines(applicationProperties.getConfigPath());

            this.mowersData = Optional.ofNullable(this.getMaxPoint(lines)).map(point -> this.getMowersData(lines, point)).orElse(null);

        }
        return this.mowersData;
    }


    private Tuple<Integer, Integer> getMaxPoint(List<String> lines) {

        return Optional.ofNullable(lines.get(0)).map(o -> {

            String[] elements = getElements(o);

            int x = Integer.parseInt(Objects.requireNonNull(elements[0]));

            int y = Integer.parseInt(Objects.requireNonNull(elements[1]));

            this.checkPoints(x, y);

            MowerRepositoryImpl.log.info("Setting MaxX = {}, MaxY = {}", x, y);

            return new Tuple<>(x, y);

        }).orElse(null);
    }

    private List<String> getFileLines(String configPath) {

        try {
            Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource(configPath)).toURI());

            return Files.lines(path).peek(o -> MowerRepositoryImpl.log.info("Reading elementes {} from {}", o, configPath)).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    private List<MowerData> getMowersData(List<String> lines, Tuple<Integer, Integer> maxPoint) {

        List<MowerData> mowersData = new ArrayList<>();

        Tuple<Integer, Integer> minPoint = new Tuple<>(this.applicationProperties.getMinX(), this.applicationProperties.getMinY());

        for (int i = 1; i < lines.size(); i += 2) {

            Position position = getPosition(lines.get(i), minPoint, maxPoint);

            List<Command> commands = getCommands(lines, i);

            MowerData mowerData = new MowerData(new Mower(position), commands);

            mowersData.add(mowerData);

        }

        return mowersData;
    }

    private List<Command> getCommands(List<String> lines, int i) {

        String commands = lines.get(++i);

        return Stream.of(commands.split(""))
                .map(Command::valueOf)
                .collect(Collectors.toList());
    }

    private Position getPosition(String s, Tuple<Integer, Integer> minPoint, Tuple<Integer, Integer> maxPoint) {

        String[] elements = getElements(s);

        assert elements[0] != null;

        assert elements[1] != null;

        assert elements[2] != null;

        Tuple<Integer,Integer> point = new Tuple<>(Integer.parseInt(elements[0]),Integer.parseInt(elements[1]));

        Orientation orientation = Orientation.valueOf(elements[2]);

        MowerRepositoryImpl.log.info("Setting position X = {}, Y = {}, Orientation = {}, MinX = {}, MaxX = {}, MinY = {}, MaxY = {}"
                , point.getLeft(), point.getRight(), orientation, minPoint.getLeft(), maxPoint.getLeft(), minPoint.getRight(), maxPoint.getRight());

        return Position.builder()
                .point(point)
                .minPoint(new Tuple<>(minPoint.getLeft(), minPoint.getRight()))
                .maxPoint(new Tuple<>(maxPoint.getLeft(), maxPoint.getRight()))
                .orientation(orientation)
                .build();
    }

    /**
     * @param s simple string to split into array of words
     * @return an array of string
     */
    private String[] getElements(String s) {
        return s.trim().split(this.applicationProperties.getElementsSeparator());
    }


    /**
     * checking that provided max x,y are not inferior than min x,y
     *
     * @param maxX max value for x
     * @param maxY max value for y
     */
    private void checkPoints(int maxX, int maxY) {
        if (maxX < this.applicationProperties.getMinX() || maxY < this.applicationProperties.getMinY()) {
            throw new IllegalArgumentException("Please provide correct values for maxX/maxY");
        }
    }
}
