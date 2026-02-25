package task1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        outContent.reset();
    }

    private String runApp(String[] args) {
        try {
            App.main(args);
        } catch (RuntimeException e) {
            return "EXCEPTION: " + e.getMessage();
        }
        return outContent.toString().trim();
    }

    @Test
    void testMissingArguments() {
        String output = runApp(new String[]{"0.5"});
        assertTrue(output.startsWith("EXCEPTION"));
    }

    @Test
    void testInvalidDouble() {
        String output = runApp(new String[]{"abc", "20"});
        assertEquals("Invalid number: abc, use double as argument", output);
    }

    @Test
    void testValidInput() {
        String output = runApp(new String[]{"0.5", "20"});
        double expected = 1.0 / Math.cos(0.5);
        assertEquals(String.valueOf(expected), output);
    }

    @Test
    void testNaNSec() {
        String output = runApp(new String[]{String.valueOf(Math.PI / 2), "20"});
        assertEquals("NaN", output);
    }
}