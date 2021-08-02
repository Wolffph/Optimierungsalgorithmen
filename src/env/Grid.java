package env;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Grid {

    private final HashMap<Coordinate, Integer> gridSystem;
    private ArrayList<Rectangle> objects;
    private ArrayList<Box> boxes;
    private final long seed;
    private final Random rnd;
    public int L;

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
        boxes = new ArrayList<>();
        this.L = L;

        for(int i = 0; i < quantity; i++){
            length = (int) Math.floor(rnd.nextDouble()*(upperBoundLength-lowerBoundLength+1)+lowerBoundLength);
            width = (int) Math.floor(rnd.nextDouble()*(upperBoundWidth-lowerBoundWidth+1)+lowerBoundWidth);
            this.objects.add(new Rectangle(L, length, width, rnd));
        }

        // placementAsGenerated(); todo: Check if this is even necessary.

        // generateFeasibleSolution(); todo: remove it later
    }

    public void generateFeasibleSolution() {

        while(!objects.isEmpty()){

            boolean alreadyAdded = false;
            Rectangle actualRect = objects.get(0);
            objects.remove(0);

            if(boxes.isEmpty()){

                Box initialBox = new Box(L, new Coordinate(0,0));

                try{
                    initialBox.acquire(actualRect);
                } catch (CloneNotSupportedException clex){
                    clex.printStackTrace();
                }

                boxes.add(initialBox);

            } else {
                for (Box ele : boxes) {
                    try{
                        if (ele.acquire(actualRect)) {
                            alreadyAdded = true;
                            break;
                        }
                    } catch (CloneNotSupportedException ie){
                        ie.printStackTrace();
                    }
                }
                if(!alreadyAdded) {
                    try{
                        Box nextBox = boxes.get(boxes.size() - 1).nextBox();
                        nextBox.acquire(actualRect);
                        boxes.add(nextBox);
                    } catch (CloneNotSupportedException ie){
                        ie.printStackTrace();
                    }

                }
            }
        }

        ArrayList<Rectangle> movedObjects = new ArrayList<>();
        for (Box box: boxes) {
            movedObjects.addAll(box.getContainer());
        }

        objects = movedObjects;
    }

    public ArrayList<Rectangle> getObjects() {
        return objects;
    }

    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    public void setObjects(ArrayList<Rectangle> objects) {
        this.objects = objects;
    }

    public void placementAsGenerated() {
        try{
            for (Rectangle rec:objects) {
                for(int i = rec.x1.getX(); i <= rec.x2.getX(); i++){
                    for(int j = rec.x1.getY(); j <= rec.y1.getY(); j++){
                        Coordinate blocking = new Coordinate(i, j);
                        int actualValue = 0;

                        try{
                            if (gridSystem.get(blocking) != null) {
                                actualValue = gridSystem.get(blocking);
                            }
                        } catch (NullPointerException nex){
                            nex.printStackTrace();
                        }
                        gridSystem.put(blocking, actualValue+1);
                    }
                }
            }
        } catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }

    public boolean violatesPlacementConstraint(Rectangle rec){
        for(int i = rec.x1.getX() + 1; i < rec.x2.getX(); i++){
            for(int j = rec.x1.getY() + 1; j < rec.y1.getY(); j++){
                int value = gridSystem.get(new Coordinate(i, j));
                if( value > 1){
                    return true;
                }
            }
        }
        return false;
    }

    public void tidyUpSingleThread(){
        int levels = (int) Math.floor(Math.sqrt(boxes.size()));
        int counter = (int) Math.floor(boxes.size()/levels);
        int elementNumber = 0;

        for(int i = 1; i <= levels+1; i++){
            for(int j = 1; j<= counter; j++){
                if(elementNumber > boxes.size() - 1){
                    break;
                } else{
                    boxes.get(elementNumber).move(new Coordinate(-L + j*L, -L + i*L));
                    elementNumber++;
                }
            }
        }
    }

    public void tidyUpMultiThread() {
        // todo: Seems like the overhead of spawning threads makes the method useless.
        // todo: Method also does not work correctly. Example: Seed=1 with 100 objects.
        int levels = (int) Math.floor(Math.sqrt(boxes.size()));
        int counter = (int) Math.floor(boxes.size()/levels);
        AtomicInteger elementNumber = new AtomicInteger();

        if(counter*levels < boxes.size()){
            counter = counter + 1;
        }


        for(int i = 1; i < levels + 1; i++){
            int finalLevels = i;
            int finalCounter = counter;
            Thread t = new Thread(()->{
                for(int j = 1; j<= finalCounter; j++){
                    if(elementNumber.get() > boxes.size() - 1){
                        break;
                    } else{
                        boxes.get(elementNumber.get()).move(new Coordinate(-L + j*L, -L + finalLevels*L));
                        elementNumber.getAndIncrement();
                    }
                }
            }, "Thread_"+levels);
            t.start();
        }
    }
}
