package be.strykers.utils.Cyclerable;

/**
 * The Cyclerable interface represents an object that can provide a Cyclerator for cycling through a sequence of elements.
 *
 * @param <T> the type of elements in the cyclerator
 */
public interface Cyclerable<T> {

    /**
     * Retrieves a Cyclerator for cycling through a sequence of elements.
     *
     * @param <T> the type of elements in the Cyclerator
     * @return the Cyclerator instance
     */
    public Cyclerator<T> getCyclerator();
}
