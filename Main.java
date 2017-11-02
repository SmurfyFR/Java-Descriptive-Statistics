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

        /* Main menu */
		System.out.println("How many values do you want to generate :");
		int n = inputInt(scanner);

		System.out.println("The following values has been generated : ");
		int[] generatedValues = DescriptiveStatistics.generateRandom(n);
		printTable(generatedValues);

        DescriptiveStatisticsDraw.draw(generatedValues);

        System.out.println("-- Descriptive Statistics : ");
        System.out.println("Max Value : " + DescriptiveStatistics.maximumValue(generatedValues));
        System.out.println("Min Value : " + DescriptiveStatistics.minimumValue(generatedValues));
        System.out.println("Mode : " + DescriptiveStatistics.modeValue(generatedValues));
        System.out.println("Median : " + DescriptiveStatistics.medianValue(generatedValues));
        System.out.println("Arithmetic mean : " + DescriptiveStatistics.arithmeticMean(generatedValues));
        System.out.println("Standard deviation : " + DescriptiveStatistics.standardDeviation(generatedValues));

        System.out.println("Drawing ...");

        DescriptiveStatisticsDraw.draw(generatedValues);
    }

    private static void printTable(int[] values) {
        for (int i = 0 ; i < values.length ; i++) {
            System.out.print(values[i] + " | ");
        }
        System.out.println();
    }
}
