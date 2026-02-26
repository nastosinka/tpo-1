package task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task3.items.Item;
import task3.space.Planet;
import task3.space.SpaceObject;
import task3.space.Universe;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    private TestItem item;
    private SpaceObject location1;
    private SpaceObject location2;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    static class TestItem extends Item {

    }

    @BeforeEach
    void setUp() {
        item = new TestItem();
        item.setQuantity(10L);

        location1 = new Planet();
        location1.setName("Локация 1");

        location2 = new Universe();
        location2.setName("Локация 2");

        originalOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    void restoreSystemOut() {
        System.setOut(originalOut);
    }

    @Test
    void changeLocation_UpdatesLocation() {
        item.changeLocation(location1);
        assertEquals(location1, item.getLocation());
    }


    @Test
    void changeLocation_PrintsMessage() {
        item.changeLocation(location1);
        assertTrue(outputStream.toString().contains("Сменена локация на Локация 1"));
    }

    @Test
    void changeLocation_MultipleChanges() {
        item.changeLocation(location1);
        item.changeLocation(location2);

        assertEquals(location2, item.getLocation());

        String output = outputStream.toString();
        assertTrue(output.contains("Локация 1"));
        assertTrue(output.contains("Локация 2"));
    }
}
