import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

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

            int counter1 = 0;
            int counter2 = 0;

            for(int i = 0; i < xCoordinates.size(); i++){
                writer.write(xCoordinates.get(i).toString());
                if(i == xCoordinates.size()-1){
                    writer.write(";");
                    writer.write("\n");
                } else{
                    writer.write(" ");
                }
                counter1 = counter1 + 1;
                System.out.println(counter1);
            }

            for(int i = 0; i < yCoordinates.size(); i++){
                writer.write(yCoordinates.get(i).toString());
                if(i == yCoordinates.size()-1){
                    writer.write(";");
                    writer.write("\n");
                } else{
                    writer.write(" ");
                }
                counter2 = counter2 + 1;
                System.out.println(counter2);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }





    }


}
