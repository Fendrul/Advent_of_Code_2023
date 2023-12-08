package be.strykers.utils;

/**
 * The Mapper class provides utility methods for mapping strings to character arrays.
 */
public class Mapper {
    /**
     * Converts a given string to an array of Characters.
     *
     * @param string The string to be converted.
     * @return An array of Characters representing the string.
     */
    public static Character[] stringToCharArray(String string) {
        return string.chars()
                .mapToObj(c -> (char) c)
                .toArray(Character[]::new);
    }
}
