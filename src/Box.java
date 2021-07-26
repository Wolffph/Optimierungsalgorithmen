import java.util.ArrayList;
import java.util.Random;

public class Box extends Rectangle{

    public final int L;

    public ArrayList<Rectangle> getContainer() {
        return container;
    }

    public ArrayList<Coordinate> getAnchorList() {
        return anchorList;
    }

    private final ArrayList<Rectangle> container;
    private final ArrayList<Coordinate> anchorList;


    public Box(int L, Coordinate lowerLeft) {
        super(L, lowerLeft);
        container = new ArrayList<>();
        anchorList = new ArrayList<>();
        this.L = L;
    }

    @Override
    public void move(Coordinate newPosition) {

        int moveLeftLowerCornerByX;
        int moveLeftLowerCornerByY;

        if(this.x1.getX() >= newPosition.getX()){
            moveLeftLowerCornerByX = -(this.x1.getX() - newPosition.getX());
        } else{
            moveLeftLowerCornerByX = newPosition.getX() - this.x1.getX();
        }

        if(this.x1.getY() >= newPosition.getY()){
            moveLeftLowerCornerByY = -(this.x1.getY() - newPosition.getY());
        } else{
            moveLeftLowerCornerByY = newPosition.getY() - this.x1.getY();
        }

        super.move(newPosition);

        for (Rectangle rect : container) {
            int leftLowerCornerOfRectX = rect.x1.getX();
            int leftLowerCornerOfRectY = rect.x1.getY();

            rect.move(new Coordinate(leftLowerCornerOfRectX + moveLeftLowerCornerByX,
                    leftLowerCornerOfRectY + moveLeftLowerCornerByY));
        }
    }

    public boolean acquire(Rectangle rect) throws CloneNotSupportedException {
        if(container.isEmpty()){
            rect.move(this.x1);
            container.add(rect);
            return true;
        } else{
            anchorList.clear();
            for (Rectangle ele : container) {
                /*
                Determination of feasible docking points for other rectangles.
                 */

                if(!anchorList.contains(ele.x2)){
                    anchorList.add(ele.x2);
                }

                if(!anchorList.contains(ele.y1)){
                    anchorList.add(ele.y1);
                }

                if(!anchorList.contains(ele.y2)){
                    anchorList.add(ele.y2);
                }
            }
            for (Coordinate coord : anchorList) {
                Rectangle backup = (Rectangle) rect.clone();
                rect.move(coord);
                if(violatesAgainstPositionConstraint(rect)){
                    rect = backup;
                } else{
                    container.add(rect);
                    return true;
                }

            }
            return false; // If this happens the box has to be "full". => Open a new Box
        }
    }

    private boolean violatesAgainstPositionConstraint(Rectangle rect){
        for (Rectangle obj : container) {
            if(!rect.equals(obj)){
                if(obj.compareTo(rect) > 0){
                   return true;
                }
            }
        }
        return !((rect.x2.getX() <= this.x2.getX()) & (rect.y2.getY() <= this.y2.getY()));
    }

    public Box nextBox() {
        Box nextBox = new Box(this.L, this.x1);
        int decider = (int) Math.round(Math.random());

        if(decider == 1){
            // Append the box to the right
            nextBox.x1 = new Coordinate(nextBox.x1.getX()+L, nextBox.x1.getY());
            nextBox.x2 = new Coordinate(nextBox.x1.getX()+L, nextBox.x1.getY());
            nextBox.y1 = new Coordinate(nextBox.x1.getX(), nextBox.x1.getY()+L);
            nextBox.y2 = new Coordinate(nextBox.x2.getX(), nextBox.y1.getY());
        } else{
            nextBox.x1 = new Coordinate(nextBox.x1.getX(), nextBox.x1.getY()+L);
            nextBox.x2 = new Coordinate(nextBox.x1.getX()+L, nextBox.x1.getY());
            nextBox.y1 = new Coordinate(nextBox.x1.getX(), nextBox.x1.getY()+L);
            nextBox.y2 = new Coordinate(nextBox.x2.getX(), nextBox.y1.getY());
        }




        /*
        System.out.println("X and Y-Coordinate for X1: " + nextBox.x1);
        System.out.println("X and Y-Coordinate for Y2: " + nextBox.y2);
        System.out.println("------");
         */

        return nextBox;
    }
}
