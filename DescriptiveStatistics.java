
public class DescriptiveStatistics {

    /**
     * Generates a pseudo-random data set
     * @param n Desired number of values
     * @return Pseudo-randomly generated dataset
     */
    public static int[] generateRandom(int n) {
        int[] randomValues = new int[n];
        for (int i = 0; i < n; i++) {
            randomValues[i] = (int) (Math.random() * 101);
        }
        return randomValues;
    }

    /**
     * Determines the maximum value of the dataset
     * @param values Dataset
     * @return Maximum value of the dataset
     */
    public static int maximumValue(int[] values) {

        int max = values[0];
        for (int i = 0; i < values.length; i++) {
            if (max < values[i]) max = values[i];
        }
        return max;
    }

    /**
     * Determines the minimum value of the dataset
     * @param values Dataset
     * @return Minimal value of the dataset
     */
    public static int minimumValue(int[] values) {
        int min = values[0];
        for (int i = 0; i < values.length; i++) {
            if (min > values[i]) min = values[i];
        }
        return min;
    }

    /**
     * Determines mode value of the dataset
     * @param values Dataset
     * @return Mode value of the dataset
     */
    public static int modeValue(int[] values) {
        int o = 0;
        int maxCount = 0;

        for (int i = 0; i < values.length; i++) {
            int count = 0;
            for (int j = 0; j < values.length; ++j) {
                if (values[j] == values[i]) ++count;
            }
            if (count > maxCount) {
                maxCount = count;
                o = values[i];
            }
        }

        return o;
    }

    /**
     * Determines the median value of the dataset
     * @param values Dataset
     * @return Median value of the dataset
     */
    public static double medianValue(int[] values) {
        // First, the array needs to be sorted
        sortValues(values);

        // Then, median can be determined
        int middle = values.length/2;
        if (values.length%2 == 1) {
            return values[middle];
        } else {
            return (values[middle-1] + values[middle]) / 2.0;
        }
    }

    /**
     * Sort given dataset using Quick-Sort algorithm
     * @param values Dataset
     */
    public static void sortValues(int[] values) {
        /* Array already sorted ... */
        if (values.length <= 1) {
            return;
        }

        int[] left = null;
        int[] right = null;
        int nleft = 0;
        int nright = 0;

        /* Select pivot */
        int pivot = values[values.length - 1];

        /* Count number of values under pivot, and the number above the pivot */
        for (int i = 0; i < values.length - 1; i++) {
            if (values[i] <= pivot) {
                nleft++;
            } else {
                nright++;
            }
        }

        /* Initialize left and right arrays */
        left = new int[nleft];
        right = new int[nright];

        /* Partition original array into two halves */
        int indexLeft = 0;
        int indexRight = 0;

        for(int i = 0; i < values.length - 1; i++) {
            if(values[i] <= pivot) {
                left[indexLeft++] = values[i];
            } else {
                right[indexRight++] = values[i];
            }
        }

        /* Use bubble sort on the two arrays */
        bubbleSort(left);
        bubbleSort(right);

        /* Merge left and right arrays */
        int index = 0;
        for(int i = 0; i < left.length; i++) {
            values[index++] = left[i];
        }
        values[index++] = pivot;
        for(int i = 0; i < right.length; i++) {
            values[index++] = right[i];
        }
    }

    /**
     * Sort dataset using Bubble-Sort algorithm
     * @param values
     */
    public static void bubbleSort(int[] values) {
        int tmp = 0;
        int n = values.length;

        for(int i = 0; i < n; i++) {
            for(int j = 1; j < (n - i); j++) {
                if(values[j-1] > values[j]) {
                    // Use the tmp variable to swap the two elements
                    tmp = values[j-1];
                    values[j-1] = values[j];
                    values[j] = tmp;
                }
            }
        }
    }

    /**
     * Calculates the arithmetic mean of the dataset
     * @param values Dataset
     * @return Arithmetic mean of the dataset
     */
    public static double arithmeticMean(int[] values) {
        double mean = 0;

        for(int i = 0; i < values.length; i++) {
            mean += values[i];
        }

        return mean / values.length;
    }

    /**
     * Calculates the standard deviation of the dataset
     * @param values Dataset
     * @return Standard deviation of the dataset
     */
    public static double standardDeviation(int[] values) {
        double deviation = 0;
        double mean = arithmeticMean(values);

        for(int i = 0; i < values.length; i++) {
            deviation += MathWrapper.pow(values[i] - mean, 2);
        }

        return MathWrapper.sqrt(deviation / values.length);
    }

    /**
     * Builds a frequency array of the given dataset
     * @param values Dataset
     * @return Frequency array
     */
    public static double[] getFrequencyArray(int[] values) {
        int max = maximumValue(values);
        double[] frequencies = new double[max + 1];

        for(int i = 0; i <= max; i++) {
            for(int j = 0; j < values.length; j++) {
                if(values[j] == i) {
                    frequencies[i]++;
                }
            }
            frequencies[i] = frequencies[i] / (double) values.length;
        }

        return frequencies;
    }

    /**
     * Determines if the given dataset is considered "low" or "high"
     * @param values Dataset
     * @return Whether the given dataset is consired "low" or "high"
     */
    public static boolean isStandardDeviationLow(int[] values) {
        int count = 0;
        double mean = arithmeticMean(values);
        double deviation = standardDeviation(values);

        for(int i = 0; i < values.length; i++) {
            if(values[i] >= (mean - deviation) && values[i] <= (mean + deviation)) {
                count++;
            }
        }

        return (count/values.length) >= 0.7;
    }
}
