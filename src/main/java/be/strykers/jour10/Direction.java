package be.strykers.jour10;

import lombok.Getter;

@Getter
public enum Direction {
    UP("up"),
    DOWN("down"),
    LEFT("left"),
    RIGHT("right");

    private String direction;

    Direction(String direction) {
        this.direction = direction;
    }

    public Direction rotateLeft() {
        return switch (this) {
            case UP -> LEFT;
            case DOWN -> RIGHT;
            case LEFT -> DOWN;
            case RIGHT -> UP;
            default -> throw new IllegalArgumentException("Unknown direction: " + this);
        };
    }

    public Direction rotateRight() {
        return switch (this) {
            case UP -> RIGHT;
            case DOWN -> LEFT;
            case LEFT -> UP;
            case RIGHT -> DOWN;
            default -> throw new IllegalArgumentException("Unknown direction: " + this);
        };
    }
}
