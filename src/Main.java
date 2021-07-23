import gui.ScriptPython;

public class Main {

    public static void main(String[] args) {
        Grid grid = new Grid();
        grid.init(1000, 30, 1, 450,
                1, 250);

        WriteToFile writer = new WriteToFile(grid.getObjects());
        writer.write();

        ScriptPython.execVisualization();
    }
}
