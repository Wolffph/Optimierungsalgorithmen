import env.Grid;
import env.Rectangle;
import gui.ScriptPython;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {



        long startTime = System.currentTimeMillis();
        Grid grid = new Grid();

        grid.init(15000, 100, 1, 10000,
                1, 10000);

        grid.generateFeasibleSolution();

        long endTime = System.currentTimeMillis();
        System.out.println("The data preparation took " + (endTime - startTime) + " milliseconds.");
        System.out.println("There are " + grid.getObjects().size() + " rectangles and we got "
                + grid.getBoxes().size() + " bounding boxes.");
        System.out.println("##################################################");


        System.out.println("TIME TO TIDY UP. Move data for better plotting.");
        startTime = System.currentTimeMillis();

        grid.tidyUpSingleThread();

        endTime = System.currentTimeMillis();
        System.out.println("TIDY UP: DONE.");
        System.out.println("The cleaning took " + (endTime - startTime) + " milliseconds.");


        System.out.println("##################################################");
        System.out.println("Proceed with writing files.");
        startTime = System.currentTimeMillis();
        WriteToFile writer = new WriteToFile(grid.getObjects());
        writer.write("data.csv");

        // Write bounding box data to a file
        ArrayList<Rectangle> boxList = new ArrayList<>(grid.getBoxes());
        WriteToFile writer2 = new WriteToFile(boxList);
        writer2.write("boxData.csv");
        endTime = System.currentTimeMillis();
        System.out.println("File writing: DONE.");
        System.out.println("The writing took " + (endTime - startTime) + " milliseconds.");






        startTime = System.currentTimeMillis();
        ScriptPython.execVisualization();
        endTime = System.currentTimeMillis();
        System.out.println("##################################################");
        System.out.println("To draw the plot it took " + (endTime - startTime) + " milliseconds.");
        System.out.println("##################################################");



    }
}
