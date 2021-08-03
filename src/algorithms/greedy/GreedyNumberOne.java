package algorithms.greedy;

import env.Box;
import env.Coordinate;
import env.Grid;
import env.Rectangle;
import gui.ScriptPython;
import jdk.jshell.spi.ExecutionControlProvider;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public class GreedyNumberOne {

    private Grid instance;
    private LinkedList<Rectangle> lengthSortedList;
    private LinkedList<Rectangle> widthSortedList;

    /**
     * A greedy algorithm to solve the given positioning problem.
     *
     *
     *
     *
     *
     *
     */


    // todo: does NOT work.
    public GreedyNumberOne(Grid instance){
        this.instance = instance;

        this.instance.getObjects().sort(Comparator.comparingInt(Rectangle::getLength));
        this.lengthSortedList = new LinkedList<>(this.instance.getObjects());

        this.instance.getObjects().sort(Comparator.comparingInt(Rectangle::getWidth));
        this.widthSortedList = new LinkedList<>(this.instance.getObjects());
    }

    public void solve() {

        boolean flag = false;
        boolean lengthFlag;
        Rectangle actualRect;
        Box newBox, actualBox;
        ArrayList<Rectangle> returnList = new ArrayList<>(this.instance.getObjects().size());


        while(true){

            newBox = new Box(instance.L, new Coordinate(0, 0));
            this.instance.getBoxes().add(newBox);
            lengthFlag = false;

            while((!lengthSortedList.isEmpty()) & (!widthSortedList.isEmpty())){

                actualBox = this.instance.getBoxes().get(this.instance.getBoxes().size() - 1);


                if(!lengthFlag){
                    actualRect = lengthSortedList.getFirst();
                    try{
                        flag = actualBox.acquire(actualRect);
                    } catch (CloneNotSupportedException clex){
                        clex.printStackTrace();
                    }
                    if(flag){
                        lengthSortedList.removeFirst();
                        widthSortedList.remove(actualRect);
                    } else{
                        lengthFlag = true;
                    }
                } else{
                    actualRect = widthSortedList.getFirst();
                    actualRect.turnRectangle();
                    try{
                        flag = actualBox.acquire(actualRect);
                    } catch (CloneNotSupportedException clex){
                        clex.printStackTrace();
                    }
                    if(flag){
                        widthSortedList.removeFirst();
                        lengthSortedList.remove(actualRect);
                    } else{
                        break;
                    }
                }
            }

            if (lengthSortedList.isEmpty()){
                if(widthSortedList.isEmpty()) {
                    break;
                }
            }
        }

        // Override objects for writing data correctly.
        for ( Box box : this.instance.getBoxes()) {
            returnList.addAll(box.getContainer());
        }

        this.instance.setObjects(returnList);

       moveRectsBetweenBoxes();


    }

    private void moveRectsBetweenBoxes(){

        // Sort Boxes ascending after container size.
        this.instance.getBoxes().sort(Comparator.comparingInt(Box::getContainerSize));

        int pointer = 0;
        int startPoint;
        boolean sign;
        ArrayList<Integer> removeIndices = new ArrayList<>();
        ArrayList<Integer> removeBoxIndices = new ArrayList<>();

        while(true){
            Box fromBox = this.instance.getBoxes().get(pointer);

            for(startPoint = pointer; startPoint < this.instance.getBoxes().size(); startPoint++){
                Box toBox = this.instance.getBoxes().get(startPoint);

                for(int j = 0; j < fromBox.getContainerSize() - 1; j++){
                    try{
                        sign = toBox.acquire(fromBox.getContainer().get(j));
                        if(sign){
                            removeIndices.add(j);
                        }
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }

            pointer++;


            // Modify fromBox
            for(int index : removeIndices){
                fromBox.getContainer().remove(index);
            }

            // Clear removeIndices
            removeIndices.clear();



            // Check if Box needs to be deleted
            if(fromBox.getContainer().isEmpty()){
                System.out.println("Box marked for deletion");
                removeBoxIndices.add(this.instance.getBoxes().indexOf(fromBox));
            }




            if(pointer == this.instance.getBoxes().size() - 1){
                break;
            }
        }

        for(int index : removeBoxIndices){
            this.instance.getBoxes().remove(index);
            }

    }


    public static void main(String[] args) throws CloneNotSupportedException {

        Grid grid = new Grid(1);

        grid.init(1000, 20, 1, 1000,
                1, 500);

        GreedyNumberOne greed = new GreedyNumberOne(grid);

        greed.solve();

        greed.instance.tidyUpSingleThread();

        greed.instance.summary();




        greed.instance.writeDataToFiles();


        ScriptPython.execVisualization();

    }
}




