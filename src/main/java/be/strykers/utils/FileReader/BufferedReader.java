package be.strykers.utils.FileReader;

import java.io.FileNotFoundException;
import java.util.logging.Logger;

public class BufferedReader implements FileReader {
    private final Logger LOGGER = Logger.getLogger(BufferedReader.class.getName());
    java.io.BufferedReader reader;

    /**
     * Creates a new FileReader that reads from the given file.
     *
     * @param fileName The name of the file to read from.
     * @throws FileNotFoundException If the file does not exist.
     */
    public BufferedReader(String fileName) throws FileNotFoundException {
        this.reader = new java.io.BufferedReader(new java.io.FileReader(fileName));
    }

    /**
     * Reads a line from the file.
     *
     * @return The line that was read from the file.
     */
    public String readLine() {
        try {
            return this.reader.readLine();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            return null;
        }
    }

    /**
     * Checks if there is a next line to read.
     *
     * @return True if there is a next line to read, false otherwise.
     */
    public boolean hasNextLine() {
        try {
            return this.reader.ready();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            return false;
        }
    }

    @Override
    public void close() throws Exception {
        this.reader.close();
    }
}
