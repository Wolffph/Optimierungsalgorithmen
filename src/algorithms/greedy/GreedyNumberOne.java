package algorithms.greedy;

import env.Grid;
import env.Rectangle;
import org.w3c.dom.css.Rect;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class GreedyNumberOne {

    private final Grid instance;
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
        // this.lengthSortedList = this.instance.getObjects();
        // this.widthSortedList = new LinkedList<Rectangle>(this.instance.getObjects().size());
    }

    public void solve(){

        /*
        Preparation (Can be moved to constructor)

        Create two Lists. One for length ordering of rectangles - Another for width ordering of rectangles
        List1 = Sort all rectangles by length ascending
        List2 = Sort all rectangles by width ascending

        START Algorithm
            Create BOX
                WHILE(TRUE):
                    Take rectangle and try positioning (without turning the rectangle)
         */

    }

    public Grid getInstance() {
        return instance;
    }


    public static void main(String[] args){

        Grid grid = new Grid();

        grid.init(1000, 102, 1, 1000,
                1, 1000);

        GreedyNumberOne greed = new GreedyNumberOne(grid);


    }
}


