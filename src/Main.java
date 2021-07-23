import gui.ScriptPython;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Grid grid = new Grid(123);
        grid.init(100, 10, 1, 45,
                1, 25);

        HashMap<Coordinate, Integer> gridsystem = grid.getGridSystem();

        for (Rectangle rect:grid.getObjects()) {
            System.out.println(grid.isEmpty(rect));
        }

        WriteToFile writer = new WriteToFile(grid.getObjects());
        writer.write();

        ScriptPython.execVisualization();
    }
}
