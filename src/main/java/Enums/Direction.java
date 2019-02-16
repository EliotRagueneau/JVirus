package Enums;

import Contents.LocatedContent;
import Controllers.Game;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public enum Direction{
    UP,
    DOWN,
    LEFT,
    RIGHT;

    private static final List<Direction> VALUES = List.of(values());
    private static final Random RANDOM = new Random();

    public static Direction randomDirection(LocatedContent c) {
        List<Direction> possibleValue = new LinkedList<>(VALUES);
        final int limit = Game.getMap().MAP_SIZE - 1;
        if (c.x == 0) {
            possibleValue.remove(LEFT);
        } else if (c.x == limit) {
            possibleValue.remove(RIGHT);
        }
        if (c.y == 0) {
            possibleValue.remove(UP);
        } else if (c.y == limit) {
            possibleValue.remove(DOWN);
        }
        return possibleValue.get(RANDOM.nextInt(possibleValue.size()));
    }
}
