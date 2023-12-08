package be.strykers.utils.FileReader;

public interface FileReader extends AutoCloseable {
    /**
     * Reads a single line of text from the input source.
     *
     * @return The next line of text from the input source.
     */
    String readLine();

    /**
     * Checks if there is another line of text available in the input source.
     *
     * @return true if there is another line of text available, false otherwise.
     */
    boolean hasNextLine();
}
