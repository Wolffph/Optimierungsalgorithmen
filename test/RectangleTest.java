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
    void printSummary() {
    }
}