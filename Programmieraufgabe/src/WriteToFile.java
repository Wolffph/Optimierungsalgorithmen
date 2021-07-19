import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class WriteToFile {

    ArrayList<Integer> xCoordinates = new ArrayList<>();
    ArrayList<Integer> yCoordinates = new ArrayList<>();;


    public WriteToFile(ArrayList<Rectangle> objectList) {
        for (Rectangle rect:objectList) {
            try{
                xCoordinates.add(rect.x1.getX());
                xCoordinates.add(rect.x2.getX());
                xCoordinates.add(rect.y1.getX());
                xCoordinates.add(rect.y2.getX());
                yCoordinates.add(rect.x1.getY());
                yCoordinates.add(rect.x2.getY());
                yCoordinates.add(rect.y1.getY());
                yCoordinates.add(rect.y2.getY());
            } catch(NullPointerException ex){
                ex.printStackTrace();
            }
        }
    }

    public void write() {
        try (FileWriter fw = new FileWriter("data.csv", StandardCharsets.UTF_8);
             BufferedWriter writer = new BufferedWriter(fw)) {

            actualWriting(writer, xCoordinates);
            actualWriting(writer, yCoordinates);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actualWriting(BufferedWriter writer, ArrayList<Integer> xCoordinates) throws IOException {
        for(int i = 0; i < xCoordinates.size(); i++){
            writer.write(xCoordinates.get(i).toString());
            if(i == xCoordinates.size()-1){
                writer.write("\n");
            } else{
                writer.write(",");
            }
        }
    }
}
