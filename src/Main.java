import gui.ScriptPython;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        Grid grid = new Grid(123);
        grid.init(100, 20, 5, 20,
                10, 30);

        // WriteToFile writer = new WriteToFile(grid.getObjects());

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
        writer.write();

        ScriptPython.execVisualization();
    }
}
