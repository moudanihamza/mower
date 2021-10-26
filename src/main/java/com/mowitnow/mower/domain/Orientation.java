package com.mowitnow.mower.domain;

/**
 * FYI order is important don't change it please.
 */
public enum Orientation {
    N,
    E,
    S,
    W;

    private static final Orientation[] orientations = values();

    public Orientation next() {
        return orientations[(this.ordinal() + 1) % orientations.length];
    }

    public Orientation previous() {
        return orientations[(this.ordinal() > 0 ? this.ordinal() : orientations.length) - 1];
    }
}
