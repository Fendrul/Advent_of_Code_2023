package be.strykers.jour10.pipes;

import lombok.Getter;

@Getter
public enum PipeType {
    HORIZONTAL('|'),
    VERTICAL('-'),
    RIGHT_UP('L'),
    RIGHT_DOWN('F'),
    LEFT_DOWN('7'),
    LEFT_UP('J'),
    GROUND('.'),
    ANIMAL('S');

    private char letter;

    PipeType(char letter) {
        this.letter = letter;
    }

    public static PipeType getLetter(char pipeType) {
        for (PipeType type : PipeType.values()) {
            if (type.getLetter() == pipeType) {
                return type;
            }
        }

        throw new IllegalArgumentException("Unknown pipe type: " + pipeType);
    }

    /**
     * Checks if any of the given `pipeTypes` is present in the `pipeTypeToVerify` array.
     *
     * @param pipeTypeToVerify an array of `PipeType` values to verify against
     * @param pipeTypes        varargs of `PipeType` values to check for presence
     * @return `true` if any `pipeType` from `pipeTypes` is present in `pipeTypeToVerify`, `false` otherwise
     */
    public static boolean findAnyFrom(PipeType pipeTypeToVerify, PipeType... pipeTypes) {
        for (PipeType pipeType : pipeTypes) {
            if (pipeTypeToVerify == pipeType) {
                return true;
            }
        }

        return false;
    }
}
