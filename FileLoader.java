import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class FileLoader {
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
