import org.w3c.dom.css.Rect;

import java.util.Random;

public class Rectangle implements Cloneable{

    public Coordinate x1;
    public Coordinate x2;
    public Coordinate y1;
    public Coordinate y2;
    private final int length;
    private final int width;
    private boolean turned = false;

    public Rectangle(int L, int length, int width){

        // If length or width is bigger than L, set value to L.
        // Assumption is allowed according to page 2 of the task file.


        // todo: Nachfragen, ob ich diese Restriktion so anwenden kann.
        if ( length > L ){
            length = L;
        }

        if ( width > L ){
            width = L;
        }

        this.length = length;
        this.width = width;

        // Create a random starting position and init the coordinates for the object.
        Random randomGenerator = new Random();
        x1 = new Coordinate(randomGenerator.nextInt(L+1), randomGenerator.nextInt(L+1));
        x2 = new Coordinate(x1.getX()+length, x1.getY());
        y1 = new Coordinate(x1.getX(), x1.getY()+width);
        y2 = new Coordinate(x2.getX(), y1.getY());

    }


    /**
     * Helper functiont which rotates the rectangle 90 degrees to the right
     * or inverts the rotated state if the rectangle has already been rotated.
     */
    public void turnRectangle(){

        if ( !turned ){
            this.x1 = x2;
            this.x2 = new Coordinate(x1.getX()+length, x1.getY());
            this.y1 = new Coordinate(x1.getX(), x1.getY()+width);
            this.y2 = new Coordinate(x2.getX(), y1.getY());
            turned = true;
        } else{
            this.x2 = x1;
            this.x1 = new Coordinate(x2.getX()-length, x2.getY());
            this.y1 = new Coordinate(x1.getX(), x1.getY()+width);
            this.y2 = new Coordinate(x2.getX(), y1.getY());
            turned = false;
        }


    }

    public void printSummary(){
        System.out.println("Summary of rectangle with hash: " + this.hashCode());
        System.out.println("---------------------------------------");
        System.out.println("x1: " + "(" + this.x1.getX() + "," + this.x1.getY() + ")");
        System.out.println("x2: " + "(" + this.x2.getX() + "," + this.x2.getY() + ")");
        System.out.println("y1: " + "(" + this.y1.getX() + "," + this.y1.getY() + ")");
        System.out.println("y2: " + "(" + this.y2.getX() + "," + this.y2.getY() + ")");
        System.out.println("---------------------------------------");
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }
        
        final Rectangle other = (Rectangle) obj;
        
        if(this.x1.equals(other.x1)){
            if(this.x2.equals(other.x2)){
                if(this.y1.equals(other.y1)){
                    return this.y2.equals(other.y2);
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (this.x1.getX() + this.x1.getY() +
                this.x2.getX() + this.x2.getY() +
                this.y1.getX() + this.y1.getY() +
                this.y2.getX() + this.y2.getY());
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}