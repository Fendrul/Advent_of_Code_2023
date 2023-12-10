package be.strykers.utils.cursors;

public class CyclicCursor<E> {
    private final E[] array;
    private int index;
    private int arrayLength;

    private CyclicCursor(E[] array) {
        if (array.length == 0) throw new IllegalArgumentException("Array must not be empty");

        this.array = array;
        this.index = 0;
        this.arrayLength = array.length;
    }

    public static <E> CyclicCursor<E> create(E[] array) {
        return new CyclicCursor<>(array);
    }

    public E next() {
        E result = array[index];
        index = (index + 1) % arrayLength;
        return result;
    }

    public E previous() {
        index = (index - 1) % arrayLength;
        if (index < 0) index += arrayLength;
        return array[index];
    }
}
