import java.awt.*;

public class DescriptiveStatisticsDraw {

    public static final int WINDOW_SIZE_WIDTH = 1024;
    public static final int WINDOW_SIZE_HEIGHT = 600;

    public static final int DIV_LEGEND_HEIGHT = 150;
    public static final int DIV_GRAPH_HEIGHT = WINDOW_SIZE_HEIGHT - DIV_LEGEND_HEIGHT;
    public static final int PADDING = 62;

    private static void initCanvas() {
        StdDraw.setCanvasSize(WINDOW_SIZE_WIDTH, WINDOW_SIZE_HEIGHT);
        StdDraw.setXscale(0, WINDOW_SIZE_WIDTH);
        StdDraw.setYscale(0, WINDOW_SIZE_HEIGHT);

        /* Set font size */
        Font currentFont = StdDraw.getFont();
        float size = 13;
        currentFont = currentFont.deriveFont(size);
        StdDraw.setFont(currentFont);
    }

    private static void drawLegendDiv(int size, int min, int max, int mode, double median, double mean, double deviation, boolean isStandardDeviationLow) {
        /* Legend box */
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.filledRectangle(0, 0, WINDOW_SIZE_WIDTH, DIV_LEGEND_HEIGHT);

        /* Legend Border-top */
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.line(0, DIV_LEGEND_HEIGHT, WINDOW_SIZE_WIDTH, DIV_LEGEND_HEIGHT);

        /* Data */
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.textLeft(3*PADDING, DIV_LEGEND_HEIGHT - 40, "Data set has " + size + " values");
        StdDraw.textLeft(3*PADDING, DIV_LEGEND_HEIGHT - 60, "Minimal value : " + min);
        StdDraw.textLeft(3*PADDING, DIV_LEGEND_HEIGHT - 80, "Maximal value : " + max);
        StdDraw.textLeft(3*PADDING, DIV_LEGEND_HEIGHT - 100, "Mode value : " + mode);
        StdDraw.textRight(WINDOW_SIZE_WIDTH - 3*PADDING, DIV_LEGEND_HEIGHT - 40, "Median : " + median);
        StdDraw.textRight(WINDOW_SIZE_WIDTH - 3*PADDING, DIV_LEGEND_HEIGHT - 60, "Arithmetic mean : " + mean);
        StdDraw.textRight(WINDOW_SIZE_WIDTH - 3*PADDING, DIV_LEGEND_HEIGHT - 80, "Standard deviation : " + deviation);
        StdDraw.textRight(WINDOW_SIZE_WIDTH - 3*PADDING, DIV_LEGEND_HEIGHT - 100,
                "Standard deviation is : " + (isStandardDeviationLow ? "low" : "high")
        );

    }

    public static void draw(int[] values) {
        /* Initialize StdDraw */
        initCanvas();

        /* Compute descriptive-statistic values */
        int min = DescriptiveStatistics.minimumValue(values);
        int max = DescriptiveStatistics.maximumValue(values);
        int mode = DescriptiveStatistics.modeValue(values);
        double median = DescriptiveStatistics.medianValue(values);
        double mean = DescriptiveStatistics.arithmeticMean(values);
        double deviation = DescriptiveStatistics.standardDeviation(values);
        boolean isStandardDeviationLow = DescriptiveStatistics.isStandardDeviationLow(values);

        /* Draw legend */
        drawLegendDiv(values.length, min, max, mode, median, mean, deviation, isStandardDeviationLow);

        /* Plot data's histogram */
        double[] frequencies = DescriptiveStatistics.getFrequencyArray(values);
        double halfHeight, halfWidth;
        double x, y;

        /* Determine the max frequency, to properly scale y-axis */
        double maxFrequency = frequencies[0];
        for (int i = 0; i < frequencies.length; i++) {
            if (maxFrequency < frequencies[i]) maxFrequency = frequencies[i];
        }
        StdDraw.textLeft(
                8, WINDOW_SIZE_HEIGHT - PADDING,
                String.format("%.2f%%", maxFrequency * 100)
        );

        StdDraw.textLeft(8, DIV_LEGEND_HEIGHT + PADDING, "0.00%");

        /* Width of the inner graph area divided by the number of unique data */
        halfWidth = ((WINDOW_SIZE_WIDTH - (2*PADDING)) / frequencies.length) / 2.0;

        for(int i = 0; i < frequencies.length; i++) {
            if(i % 2 == 0) StdDraw.setPenColor(StdDraw.RED);
            else StdDraw.setPenColor(StdDraw.BLUE);

            /* Frequency * inner height of graph */
            halfHeight = ((frequencies[i] / maxFrequency) * (DIV_GRAPH_HEIGHT - (2*PADDING))) / 2;

            /* Origin of the graph + (width / 2)*(i + 1) */
            x = PADDING + (halfWidth) * (2*i + 1);

            /* Origin + halfHeight */
            y = (DIV_LEGEND_HEIGHT + PADDING) + halfHeight;

            StdDraw.rectangle(x, y, halfWidth, halfHeight);

            if(i % 20 == 0) {
                StdDraw.text(x, DIV_LEGEND_HEIGHT + PADDING - 15, Integer.toString(i));
            }
        }

        /* Plot computed data */
        /* - Mode */
        StdDraw.setPenColor(StdDraw.MAGENTA);
        x = PADDING + (halfWidth) * (2*mode + 1);
        y = DIV_LEGEND_HEIGHT + PADDING;
        StdDraw.line(x, y, x, y - 10);
        StdDraw.text(x, y - 25, "Mode", 35);

        /* - Median */
        StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
        x = PADDING + (halfWidth) * (2*median + 1);
        y = DIV_LEGEND_HEIGHT + PADDING;
        StdDraw.line(x, y, x, y - 15);
        StdDraw.text(x, y - 30, "Median", 35);

        /* - Arithmetic Mean */
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        x = PADDING + (halfWidth) * (2*mean + 1);
        y = DIV_LEGEND_HEIGHT + PADDING;
        StdDraw.line(x, y, x, y - 30);
        StdDraw.text(x, y - 45, "Mean", 35);

        /* - Standard Deviation */
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        x = PADDING + (halfWidth) * (2*deviation + 1);
        y = DIV_LEGEND_HEIGHT + PADDING;
        StdDraw.line(x, y, x, y - 20);
        StdDraw.text(x, y - 35, "Deviation", 35);

        /* X-Axis */
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        StdDraw.line(
                PADDING, DIV_LEGEND_HEIGHT + PADDING,
                WINDOW_SIZE_WIDTH - PADDING + 20, DIV_LEGEND_HEIGHT + PADDING
        );

        /* Y-Axis */
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        StdDraw.line(
                PADDING, DIV_LEGEND_HEIGHT + PADDING,
                PADDING, WINDOW_SIZE_HEIGHT - PADDING + 20
        );

        StdDraw.show();
    }

}
