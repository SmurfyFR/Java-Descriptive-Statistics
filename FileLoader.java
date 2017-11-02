import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class FileLoader {
    public static int[] loadDatasetFromFile(String filepath) {
        int[] values = null;
        int numberOfLines = 0;
        Scanner fileIn = null;

        /* First count number of lines */
        try {
            fileIn = new Scanner(new File(filepath));
        } catch(FileNotFoundException e) {
            System.out.println("Cannot open file : '" + filepath + "'.");
            System.exit(1);
        }

        while(fileIn.hasNextInt()) {
            numberOfLines++;
            fileIn.nextLine();
        }

        /* Then build values array with each line */
        try {
            fileIn = new Scanner(new File(filepath));
        } catch(FileNotFoundException e) {
            System.out.println("Cannot open file : '" + filepath + "'.");
            System.exit(1);
        }

        values = new int[numberOfLines];
        int i = 0;
        while(fileIn.hasNextInt()) {
            values[i++] = fileIn.nextInt();
        }

        return values;
    }
}
