import java.awt.*;

public class DescriptiveStatisticsDraw {

    public static final int WINDOW_SIZE_WIDTH = 1024;
    public static final int WINDOW_SIZE_HEIGHT = 600;

    public static final int DIV_LEGEND_HEIGHT = 150;
    public static final int DIV_GRAPH_HEIGHT = WINDOW_SIZE_HEIGHT - DIV_LEGEND_HEIGHT;
    public static final int PADDING = 62;

    public static void draw(int[] values) {
        StdDraw.setCanvasSize(WINDOW_SIZE_WIDTH, WINDOW_SIZE_HEIGHT);
        StdDraw.setXscale(0, WINDOW_SIZE_WIDTH);
        StdDraw.setYscale(0, WINDOW_SIZE_HEIGHT);

        /* Legend box */
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.filledRectangle(0, 0, WINDOW_SIZE_WIDTH, DIV_LEGEND_HEIGHT);

        /* Legend Border-top */
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.line(0, DIV_LEGEND_HEIGHT, WINDOW_SIZE_WIDTH, DIV_LEGEND_HEIGHT);

        /* Dummy rectangle data */
        double[] frequencies = DescriptiveStatistics.getFrequencyArray(values);
        double halfHeight, halfWidth;
        double x, y;

        double maxFrequency = frequencies[0];
        for (int i = 0; i < frequencies.length; i++) {
            if (maxFrequency < frequencies[i]) maxFrequency = frequencies[i];
        }

        /* Width of the inner graph area divided by the number of unique data */
        halfWidth = ((WINDOW_SIZE_WIDTH - (PADDING)) / frequencies.length) / 2.0;

        for(int i = 0; i < frequencies.length; i++) {
            if(i % 2 == 0) StdDraw.setPenColor(StdDraw.RED);
            else StdDraw.setPenColor(StdDraw.BLUE);

            /* Frequency * inner height of graph */
            System.out.println(frequencies[i] + "/" + maxFrequency);
            halfHeight = ((frequencies[i] / maxFrequency) * (DIV_GRAPH_HEIGHT - (2*PADDING))) / 2;

            /* Origin of the graph + (width / 2)*(i + 1) */
            x = PADDING + (halfWidth) * (2*i + 1);

            /* Origin + halfHeight */
            y = (DIV_LEGEND_HEIGHT + PADDING) + halfHeight;

            StdDraw.rectangle(x, y, halfWidth, halfHeight);

        }

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
