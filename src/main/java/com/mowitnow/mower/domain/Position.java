package com.mowitnow.mower.domain;

import com.mowitnow.mower.utils.Tuple;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@EqualsAndHashCode
@Slf4j
@Builder
@Getter
public class Position {

    private final Tuple<Integer, Integer> point;

    private final Tuple<Integer, Integer> minPoint;

    private final Tuple<Integer, Integer> maxPoint;

    private Orientation orientation;

    public void incrementX() {

        final int current = this.point.getLeft();

        if (current < this.maxPoint.getLeft()) {


            this.point.setLeft(current + 1);

            Position.log.info("Position X incremented from {} to {}", current, this.point.getLeft());

        } else {

            Position.log.info("Position reached its maximum X {}", current);
        }
    }

    public void decrementX() {

        final int current = this.point.getLeft();

        if (current > this.minPoint.getLeft()) {


            this.point.setLeft(current - 1);
            ;

            Position.log.info("Position X decremented from {} to {}", current, this.point.getLeft());

        } else {

            Position.log.info("Position reached its minimum X {}", current);
        }
    }

    public void incrementY() {

        final int current = this.point.getRight();

        if (current < this.maxPoint.getRight()) {

            this.point.setRight(current + 1);

            Position.log.info("Position Y incremented from {} to {}", current, this.point.getRight());

        } else {

            Position.log.info("Position reached its maximum Y {}", current);
        }
    }

    public void decrementY() {

        final int current = this.point.getRight();

        if (current > this.minPoint.getRight()) {

            this.point.setRight(current - 1);

            Position.log.info("Position Y decremented from {} to {}", current, this.point.getRight());

        } else {

            Position.log.info("Position reached its minimum Y {}", current);
        }
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public String toString() {
        return this.point.getLeft() + " " + this.point.getRight() + " " + this.orientation;
    }
}
