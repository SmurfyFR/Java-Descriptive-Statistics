import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class FileLoader {
    /**
     * Opens a Scanner instance for a given file path
     * @param filepath File path relative to current working directory
     * @return Scanner instance for the given file path
     */
    protected static Scanner openFileHandle(String filepath) {
        Scanner fileIn = null;

        try {
            fileIn = new Scanner(new File(filepath));
        } catch(FileNotFoundException e) {
            System.out.println("Cannot open file : '" + filepath + "'.");
            System.exit(1);
        }

        return fileIn;
    }

    /**
     * Load integer numbers from a file.
     * The file MUST have one integer per line, otherwise the file won't be parsed correctly.
     * @param filepath File path relative to current working directory
     * @return Array of all integer values contained in the file
     */
    public static int[] loadDatasetFromFile(String filepath) {
        int[] values = null;
        int numberOfLines = 0;
        Scanner fileIn = null;

        /* First count number of lines */
        fileIn = openFileHandle(filepath);

        while(fileIn.hasNextInt()) {
            numberOfLines++;
            fileIn.nextLine();
        }

        /* Then build values array with each line */
        fileIn.close();
        fileIn = openFileHandle(filepath);

        values = new int[numberOfLines];
        int i = 0;
        while(fileIn.hasNextInt()) {
            values[i++] = fileIn.nextInt();
        }

        return values;
    }
}
