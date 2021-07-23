import gui.ScriptPython;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {

        long startTime = System.currentTimeMillis();

        Grid grid = new Grid();
        grid.init(100, 1000, 5, 20,
                10, 30);


        // Init list for bounding boxes
        ArrayList<Box> boxes = new ArrayList<>();

        // Get rectangles and asign inital valid positions
        ArrayList<Rectangle> rectangleList = grid.getObjects();

        while(!rectangleList.isEmpty()){

            boolean alreadyAdded = false;
            Rectangle actualRect = rectangleList.get(0);
            rectangleList.remove(0);

            if(boxes.isEmpty()){
                boxes.add(new Box(grid.L, new Coordinate(0,0)));
            } else {
                for (Box ele : boxes) {
                        if (ele.acquire(actualRect)) {
                            alreadyAdded = true;
                            break;
                        }
                }
                if(!alreadyAdded) {
                    Box nextBox = boxes.get(boxes.size() - 1).nextBox();
                    nextBox.acquire(actualRect);
                    boxes.add(nextBox);
                }
            }
        }

        ArrayList<Rectangle> movedObjects = new ArrayList<>();
        for (Box box: boxes
             ) {
            movedObjects.addAll(box.getContainer());

        }
        WriteToFile writer = new WriteToFile(movedObjects);

        // WriteToFile writer = new WriteToFile(grid.getObjects());
        writer.write("data.csv");

        // Write bounding box data to a file
        ArrayList<Rectangle> boxList = new ArrayList<>(boxes);
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
