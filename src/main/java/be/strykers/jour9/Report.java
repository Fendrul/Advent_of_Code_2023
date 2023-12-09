package be.strykers.jour9;

import be.strykers.utils.Logger.LoggerBuilder;
import lombok.Builder;
import lombok.Getter;

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

    public int getNextValuePredicted() {
        return reduceArray(numbers);
    }

    private int reduceArray(int[] array) {
        if (containsAllZeroes(array)) return 0;

        LOGGER.fine("Reducing array: " + Arrays.toString(array));

        int arrayLength = array.length;
        int[] newArrayOfNumbers = new int[arrayLength - 1];

        for (int i = 0; i < arrayLength - 1; i++) {
            newArrayOfNumbers[i] = array[i + 1] - array[i];
        }

        int reducedArray = reduceArray(newArrayOfNumbers);

        return reducedArray + array[arrayLength - 1];
    }

    private boolean containsAllZeroes(int[] array) {
        for (int number : array) {
            if (number != 0) return false;
        }

        return true;
    }
}
