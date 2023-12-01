package be.strykers.utils.FileReader;

import java.io.FileNotFoundException;

public class BufferedReader implements FileReader {
    java.io.BufferedReader reader;

    /**
     * Creates a new FileReader that reads from the given file.
     * @param fileName The name of the file to read from.
     * @throws FileNotFoundException If the file does not exist.
     */
    public BufferedReader(String fileName) throws FileNotFoundException {
        this.reader = new java.io.BufferedReader(new java.io.FileReader(fileName));
    }

    /**
     * Reads a line from the file.
     * @return The line that was read from the file.
     */
    public String readLine() {
        try {
            return this.reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Checks if the file has reached the end of the file.
     * @return true if the file has reached the end of the file, false otherwise.
     */
    public boolean endOfFile() {
        try {
            return !this.reader.ready();
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
}
