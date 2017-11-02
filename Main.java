import java.util.Scanner;

public class Main {

	public static int inputInt(Scanner s) {
		while(!s.hasNextInt()) {
			s.next();
		}
		return s.nextInt();
	}

    public static void main(String[] args) {
	    /* Initialization */
	    MathWrapper.useJavaMath(false);
        Scanner scanner = new Scanner(System.in);

        /* Generate data or get it from a file ? */
        int choice = 0;
        System.out.println("[1] - Generate dataset");
        System.out.println("[2] - Import dataset from file");
        do {
             choice = inputInt(scanner);
        } while(choice != 1 && choice != 2);

        int[] values = null;
        if(choice == 1) {
            System.out.println("How many values do you want to generate :");
            int n = inputInt(scanner);
            values = DescriptiveStatistics.generateRandom(n);
        } else if(choice == 2) {
            // import from file
            System.out.println("Please enter the file path :");
            scanner.nextLine(); // Empty buffer before
            String filePath = scanner.nextLine();

            values = FileLoader.loadDatasetFromFile(filePath);
        }

        System.out.println("\nThe following " + values.length + " values has been loaded : ");
        printTable(values);

        System.out.println("-- Descriptive Statistics : ");
        System.out.println("Max Value : " + DescriptiveStatistics.maximumValue(values));
        System.out.println("Min Value : " + DescriptiveStatistics.minimumValue(values));
        System.out.println("Mode : " + DescriptiveStatistics.modeValue(values));
        System.out.println("Median : " + DescriptiveStatistics.medianValue(values));
        System.out.println("Arithmetic mean : " + DescriptiveStatistics.arithmeticMean(values));
        System.out.println("Standard deviation : " + DescriptiveStatistics.standardDeviation(values));
        System.out.print("Standard deviation is : ");
        if(DescriptiveStatistics.isStandardDeviationLow(values)) {
            System.out.println("low.");
        } else {
            System.out.println("high.");
        }

        System.out.println("Drawing ...");

        DescriptiveStatisticsDraw.draw(values);
    }

    private static void printTable(int[] values) {
        for (int i = 0 ; i < values.length ; i++) {
            System.out.print(values[i] + " | ");
        }
        System.out.println();
    }
}
