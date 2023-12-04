package be.strykers.utils.enums;

public enum Colors {
    blue(0, "blue"),
    green(1, "green"),
    red(2, "red");

    private final int id;
    private final String name;

    Colors(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
