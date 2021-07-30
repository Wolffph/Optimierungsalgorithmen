package env;

import java.util.Random;

public class Rectangle implements Cloneable, Comparable{

    public Coordinate x1, x2;
    public Coordinate y1, y2;
    private final int length, width;
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
     * Constructor with seed.
     */
    public Rectangle(int L, int length, int width, Random randomGenerator){

        // If length or width is bigger than L, set value to L.
        // Assumption is allowed according to page 2 of the task file.

        if ( length > L ){
            length = L;
        }

        if ( width > L ){
            width = L;
        }

        this.length = length;
        this.width = width;

        // Create a random starting position and init the coordinates for the object.
        x1 = new Coordinate(randomGenerator.nextInt(L+1), randomGenerator.nextInt(L+1));
        x2 = new Coordinate(x1.getX()+length, x1.getY());
        y1 = new Coordinate(x1.getX(), x1.getY()+width);
        y2 = new Coordinate(x2.getX(), y1.getY());

    }

    /**
     * Constructor for boxes.
     */
    public Rectangle(int L, Coordinate lowerLeft){

        this.length = L;
        this.width = L;
        x1 = new Coordinate(lowerLeft.getX(), lowerLeft.getY());
        x2 = new Coordinate(x1.getX()+L, x1.getY());
        y1 = new Coordinate(x1.getX(), x1.getY()+L);
        y2 = new Coordinate(x2.getX(), y1.getY());

    }


    /**
     * Helper function which rotates the rectangle 90 degrees to the right
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

    /**
     * Moves the current rectangle to a new position, based on the lower left corner.
     */
    public void move(Coordinate newPosition){
        if(newPosition != null){
            this.x1 = newPosition;
            this.x2 = new Coordinate(x1.getX()+length, x1.getY());
            this.y1 = new Coordinate(x1.getX(), x1.getY()+width);
            this.y2 = new Coordinate(x2.getX(), y1.getY());
        }
    }


    /**
     * This function gives a summary of the object. It prints all four coordinates from the present rectangle
     * as well as its hashCode.
     */
    public void printSummary(){
        System.out.println("Summary of rectangle with hash: " + this.hashCode());
        System.out.println("---------------------------------------");
        System.out.println("x1: " + "(" + this.x1.getX() + "," + this.x1.getY() + ")");
        System.out.println("x2: " + "(" + this.x2.getX() + "," + this.x2.getY() + ")");
        System.out.println("y1: " + "(" + this.y1.getX() + "," + this.y1.getY() + ")");
        System.out.println("y2: " + "(" + this.y2.getX() + "," + this.y2.getY() + ")");
        System.out.println("---------------------------------------");
    }

    /**
     * The function calculates the volume of the rectangle.
     */
    public int volume(){
        return this.length * this.width;
    }

    public int getLength() {
        return this.length;
    }

    public int getWidth() {
        return this.width;
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
        return x1.hashCode() + x2.hashCode() + y1.hashCode() + y2.hashCode();
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(Object o) {

        if( ((Rectangle) o).x1.getX() >= this.x2.getX() ){
            return -1;
        } else if( ((Rectangle) o).x2.getX() <= this.x1.getX() ){
            return -1;
        } else{
            return 1;
        }
    }
}