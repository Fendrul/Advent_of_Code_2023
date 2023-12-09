package be.strykers.jour9;

import be.strykers.utils.Logger.LoggerBuilder;
import lombok.Builder;
import lombok.Getter;
import org.javatuples.Pair;

import java.util.Arrays;
import java.util.logging.Logger;

@Getter
@Builder
public class Report {
    private final static Logger LOGGER = LoggerBuilder.getLogger();

    int[] numbers;

    public Report(int[] numbers) {
        if (numbers == null) throw new IllegalArgumentException("numbers cannot be null");
        if (numbers.length < 2) throw new IllegalArgumentException("numbers must contain at least 2 elements");

        this.numbers = numbers;
    }

    public Pair<Integer, Integer> getNextValuePredicted() {
        return reduceArray(numbers);
    }

    /**
     * Reduces an array of integers by subtracting each element from its next element.
     * If the array only contains zeroes, the result will be a pair (0, 0).
     * Otherwise, it recursively reduces the array until it only contains one element.
     * The final pair returned is the difference between the first element of the original array and the reduced array,
     * and the sum of the last element of the original array and the reduced array.
     *
     * @param array the array of integers to be reduced
     * @return a pair with the reduced values
     */
    private Pair<Integer, Integer> reduceArray(int[] array) {
        if (containsAllZeroes(array)) return Pair.with(0, 0);

        int arrayLength = array.length;
        int[] newArrayOfNumbers = new int[arrayLength - 1];

        for (int i = 0; i < arrayLength - 1; i++) {
            newArrayOfNumbers[i] = array[i + 1] - array[i];
        }

        Pair<Integer, Integer> reducedArray = reduceArray(newArrayOfNumbers);

        Pair<Integer, Integer> result = Pair.with(
                array[0] - reducedArray.getValue0(),
                array[arrayLength - 1] + reducedArray.getValue1()
        );

        LOGGER.finer("For array " + Arrays.toString(array) + "\nThe result is " + result.toString() + " and the reduced array is " + Arrays.toString(newArrayOfNumbers) + "\n");

        return result;
    }

    private boolean containsAllZeroes(int[] array) {
        for (int number : array) {
            if (number != 0) return false;
        }

        return true;
    }
}
