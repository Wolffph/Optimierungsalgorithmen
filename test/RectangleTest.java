import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {
    private Rectangle firstRect, secRect;

    @BeforeEach
    void generateObjects(){
        firstRect = new Rectangle(1000, 100, 100);
        secRect = new Rectangle(1000, 100, 100);
    }

    @Test
    void turnRectangle() throws CloneNotSupportedException {

        // First test: Create a rectangle, turn it and compare it to the first state.
        Rectangle prev = (Rectangle) firstRect.clone();
        prev.turnRectangle();
        assertNotEquals(prev, firstRect, "The rectangle has not turned!");

        // Second test: Turn the rectangle again. Now it should be in the first state.
        prev.turnRectangle();
        assertEquals(prev, firstRect, "The rectangle should be in the first position again.");
    }

    @Test
    void volume() {
        assertEquals(firstRect.getLength()* firstRect.getWidth(), firstRect.volume());
    }

    @Test
    void move() throws CloneNotSupportedException {
        Grid grid = new Grid(100);
        grid.init(1000, 10, 100, 100,
                100, 200);
        Rectangle beforeMoving = (Rectangle) grid.getObjects().get(0).clone();
        grid.getObjects().get(0).move(new Coordinate(2500, 10));

        assertNotEquals(beforeMoving, grid.getObjects().get(0));
        assertEquals(grid.getObjects().get(0).x1, new Coordinate(2500, 10));
    }
}