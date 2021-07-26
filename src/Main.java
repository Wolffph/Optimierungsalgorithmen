import gui.ScriptPython;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {

        long startTime = System.currentTimeMillis();

        Grid grid = new Grid(1);

        grid.init(150, 50, 1, 100,
                1, 100);


        System.out.println("There are " + grid.getObjects().size() + " retangles.");
        System.out.println("And we got " + grid.getBoxes().size() + " bounding boxes.");

        System.out.println("Time to tidy up: ");
        System.out.println("---------------------");
        grid.tidyUpSingleThread();
        // WriteToFile writer = new WriteToFile(movedObjects);

        WriteToFile writer = new WriteToFile(grid.getObjects());
        writer.write("data.csv");

        // Write bounding box data to a file
        ArrayList<Rectangle> boxList = new ArrayList<>(grid.getBoxes());
        WriteToFile writer2 = new WriteToFile(boxList);
        writer2.write("boxData.csv");



        long endTime = System.currentTimeMillis();
        System.out.println("The inital calculation took " + (endTime - startTime) + " milliseconds.");

        startTime = System.currentTimeMillis();
        ScriptPython.execVisualization();
        endTime = System.currentTimeMillis();
        System.out.println("To draw the plot it took " + (endTime - startTime) + " milliseconds.");




    }
}
