import java.util.ArrayList;
import java.util.Random;

public class Grid {

    private ArrayList<Rectangle> objects;
    private final long seed;
    private final Random rnd;

    public Grid(long seed){
        this.seed = seed;
        rnd = new Random(this.seed);
    }

    public Grid(){
        rnd = new Random();
        seed = rnd.nextLong();
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
    }

    public ArrayList<Rectangle> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<Rectangle> objects) {
        this.objects = objects;
    }
}
