package be.strykers.utils;

public class Mapper {
    public static Character[] stringToCharArray(String string) {
        return string.chars()
                .mapToObj(c -> (char) c)
                .toArray(Character[]::new);
    }
}
