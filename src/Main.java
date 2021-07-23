import gui.ScriptPython;
import java.util.ArrayList;

public class Main {


    public static ArrayList<Rectangle> init(int L, int quantity, int lowerBoundLength, int upperBoundLength,
                     int lowerBoundWidth, int upperBoundWidth){

        int length;
        int width;
        ArrayList<Rectangle> objectList = new ArrayList<>(L);

        for(int i = 0; i < quantity; i++){
            length = (int) Math.floor(Math.random()*(upperBoundLength-lowerBoundLength+1)+lowerBoundLength);
            width = (int) Math.floor(Math.random()*(upperBoundWidth-lowerBoundWidth+1)+lowerBoundWidth);
            objectList.add(new Rectangle(L, length, width));
        }


        return objectList;

    }



    public static void main(String[] args) {
        ArrayList<Rectangle> objects = init(50, 7, 1, 100,
                1, 100);

        WriteToFile writer = new WriteToFile(objects);
        writer.write();

        ScriptPython.execVisualization();


    }
}
