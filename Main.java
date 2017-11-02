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

        /* Main menu */
    	int choice = 0;
		System.out.println("Enter n :");
		Scanner scanner = new Scanner(System.in);

		int n = inputInt(scanner);

		System.out.println("n = " + n);
		//int[] generatedValues = DescriptiveStatistics.generateRandom(n);
		int[] generatedValues = new int[] {1,2,3,4,5};
		DescriptiveStatistics.printTable(generatedValues);

        DescriptiveStatisticsDraw.draw(generatedValues);
/**
		do {
			System.out.println("\nOPTIONS : ");
			System.out.println("1. Get Maximum Value");
			System.out.println("2. Get Minimum Value");
			System.out.println("3. Get Mode");
			System.out.println("4. Get Median");
            System.out.println("5. Get Arithmetic Mean");
            System.out.println("6. Get Standard deviation");
            System.out.println("7. Draw graph");
			System.out.println("0. Quit\n");

			choice = inputInt(scanner);

			switch (choice) {
				case 1:
					System.out.println("Max Value " + DescriptiveStatistics.maximumValue(generatedValues));
					break;
				case 2:
					System.out.println("Min Value " + DescriptiveStatistics.minimumValue(generatedValues));
					break;
				case 3:
					System.out.println("Mode " + DescriptiveStatistics.modeValue(generatedValues));
					break;
				case 4:
                    System.out.println("Median " + DescriptiveStatistics.medianValue(generatedValues));
					break;
                case 5:
                    System.out.println("Arithmetic mean " + DescriptiveStatistics.arithmeticMean(generatedValues));
                    break;
                case 6:
                    System.out.println("Standard deviation " + DescriptiveStatistics.standardDeviation(generatedValues));
                    break;
                case 7:
                    System.out.println("Drawing ...");
                    DescriptiveStatisticsDraw.draw(generatedValues);
                    break;
				case 0:
					System.out.println("Exiting program");
					break;
				default:
					System.out.println("Invalid choice");
					break;
			}
		} while (choice != 0); */
    }
}
