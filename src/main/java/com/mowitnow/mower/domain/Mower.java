package com.mowitnow.mower.domain;


import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Value
public class Mower implements MowerOperation {

    Position position;

    @Override
    public void move() {

        switch (this.position.getOrientation()) {

            case N:
                this.position.incrementY();
                break;

            case E:
                this.position.incrementX();
                break;

            case S:
                this.position.decrementY();
                break;

            case W:
                this.position.decrementX();
                break;

            default:
                Mower.log.error("Sorry! i don't know how to handle the given orientation {}", this.position.getOrientation());
        }
    }

    @Override
    public void right() {

        Orientation current = this.getPosition().getOrientation();

        Orientation next = current.next();

        Mower.log.info("Changing Orientation from {} to {}", current, next);

        this.getPosition().setOrientation(next);

    }

    @Override
    public void left() {

        Orientation current = this.getPosition().getOrientation();

        Orientation previous = current.previous();

        Mower.log.info("Changing Orientation from {} to {}", current, previous);

        this.getPosition().setOrientation(previous);
    }

    @Override
    public String toString() {
        return this.position.toString();
    }
}
