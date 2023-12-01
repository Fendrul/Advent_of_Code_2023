package be.strykers.utils.FileReader;

public enum Number {
    one ("one", 1),
    two ("two", 2),
    three ("three", 3),
    four ("four", 4),
    five ("five", 5),
    six ("six", 6),
    seven ("seven", 7),
    eight ("eight", 8),
    nine ("nine", 9);

    private final String name;
    private final int value;

    private Number(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() { return name; }
    public int getValue() { return value; }
}
