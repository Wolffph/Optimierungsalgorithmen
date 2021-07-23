import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Grid {

    private final HashMap<Coordinate, Integer> gridSystem;
    private ArrayList<Rectangle> objects;
    private final long seed;
    private final Random rnd;

    public Grid(long seed){
        this.seed = seed;
        rnd = new Random(this.seed);
        gridSystem = new HashMap<>();
    }

    public Grid(){
        rnd = new Random();
        seed = rnd.nextLong();
        gridSystem = new HashMap<>();
    }


    public HashMap<Coordinate, Integer> getGridSystem() {
        return gridSystem;
    }

    public void init(int L, int quantity, int lowerBoundLength, int upperBoundLength,
                                            int lowerBoundWidth, int upperBoundWidth){

        int length;
        int width;
        this.objects = new ArrayList<>(L);

        for(int i = 0; i < quantity; i++){
            length = (int) Math.floor(rnd.nextDouble()*(upperBoundLength-lowerBoundLength+1)+lowerBoundLength);
            width = (int) Math.floor(rnd.nextDouble()*(upperBoundWidth-lowerBoundWidth+1)+lowerBoundWidth);
            this.objects.add(new Rectangle(L, length, width, rnd));
        }

        placement();
    }

    public ArrayList<Rectangle> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<Rectangle> objects) {
        this.objects = objects;
    }

    public void placement() {
        try{
            for (Rectangle rec:objects) {
                for(int i = rec.x1.getX(); i <= rec.x2.getX(); i++){
                    for(int j = rec.x1.getY(); j <= rec.y1.getY(); j++){
                        Coordinate blocking = new Coordinate(i, j);
                        int actualValue;
                        try{
                           actualValue = gridSystem.get(blocking);
                        } catch (NullPointerException ex){
                           actualValue = 0;
                        }
                        gridSystem.put(blocking, actualValue+1);
                    }
                }
            }
        } catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }

    public boolean isEmpty(Rectangle rec){
        for(int i = rec.x1.getX() + 1; i < rec.x2.getX(); i++){
            for(int j = rec.x1.getY() + 1; j < rec.y1.getY(); j++){
                int value = gridSystem.get(new Coordinate(i, j));
                if( value > 1){
                    return false;
                }
            }
        }
        return true;
    }
}
