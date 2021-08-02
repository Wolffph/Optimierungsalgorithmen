package algorithms.greedy;

import env.Box;
import env.Coordinate;
import env.Grid;
import env.Rectangle;
import gui.ScriptPython;

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


    public GreedyNumberOne(Grid instance){
        this.instance = instance;

        this.instance.getObjects().sort(Comparator.comparingInt(Rectangle::getLength));
        this.lengthSortedList = new LinkedList<>(this.instance.getObjects());

        this.instance.getObjects().sort(Comparator.comparingInt(Rectangle::getWidth));
        this.widthSortedList = new LinkedList<>(this.instance.getObjects());
    }

    public void solve() {

        boolean flag = false;
        boolean lengthFlag = false;
        Rectangle actualRect;
        Box newBox;

        while(true){

            newBox = new Box(instance.L, new Coordinate(0, 0));
            lengthFlag = false;

            while((!lengthSortedList.isEmpty()) & (!widthSortedList.isEmpty())){

                if(!lengthFlag){
                    actualRect = lengthSortedList.getFirst();
                    try{
                        flag = newBox.acquire(actualRect);
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
                    try{
                        flag = newBox.acquire(actualRect);
                    } catch (CloneNotSupportedException clex){
                        clex.printStackTrace();
                    }
                    if(flag){
                        widthSortedList.removeFirst();
                        lengthSortedList.remove(actualRect);
                    } else{
                        this.instance.getBoxes().add(newBox);
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

        /*
        START Algorithm
            Create BOX
                WHILE(TRUE):
                    Take rectangle and try positioning (without turning the rectangle)
         */

    }


    public static void main(String[] args){

        Grid grid = new Grid(123);

        grid.init(1000, 100, 1, 1000,
                1, 1000);

        GreedyNumberOne greed = new GreedyNumberOne(grid);
        greed.solve();
        greed.instance.tidyUpSingleThread();


        greed.instance.writeDataToFiles();


        ScriptPython.execVisualization();

    }
}




