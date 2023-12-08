package be.strykers.utils.Cyclerable;

/**
 * The Cyclerator interface represents an object that can cycle through a sequence of elements and provide the current index.
 *
 * @param <T> the type of elements in the Cyclerator
 */
public interface Cyclerator<T> {

    /**
     * Returns the next element in the cycle.
     *
     * @return the next element in the cycle
     */
    public T cycle();

    /**
     * Returns the current index of the element in the cycle.
     *
     * @return the current index of the element in the cycle
     */
    public int getIndex();
}
