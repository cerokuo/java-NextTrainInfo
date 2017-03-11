package com.subway.got;

/**
 * Created by cerokuo on 10/03/2017.
 */
public enum Direction {

    NORTH("North"),
    SOUTH("South"),
    EAST("East"),
    WEST("West");

    private final String text;

    /**
     * @param text
     */
    private Direction(final String text) {
        this.text = text;
    }

    /**
     * Enum to String.
     * @return
     */
    @Override
    public String toString() {
        return text;
    }
}
