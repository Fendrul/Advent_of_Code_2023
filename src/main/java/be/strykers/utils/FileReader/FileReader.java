package be.strykers.utils.FileReader;

public interface FileReader extends AutoCloseable {
    String readLine();

    boolean hasNextLine();
}
