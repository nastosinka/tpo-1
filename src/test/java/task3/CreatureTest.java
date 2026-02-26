package task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task3.items.Creature;
import task3.types.DeathType;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import static org.junit.jupiter.api.Assertions.*;

class CreatureTest {

    private Creature creature;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        creature = new Creature();

        originalOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    void restoreSystemOut() {
        System.setOut(originalOut);
    }

    @Test
    void die_WithChokingType_PrintsMessage() {
        creature.die(DeathType.CHOKING);
        assertTrue(outputStream.toString().contains("Он погиб от удушья."));
    }

    @Test
    void die_WithSurpriseType_PrintsMessage() {
        creature.die(DeathType.SURPRISE);
        assertTrue(outputStream.toString().contains("Он умер от удивления."));
    }

    @Test
    void die_SetsDeathType() {
        creature.die(DeathType.CHOKING);
        assertEquals(DeathType.CHOKING, creature.getDeathType());
    }

    @Test
    void die_SetsIsAliveToFalse() {
        creature.setAlive(true);
        creature.die(DeathType.CHOKING);
        assertFalse(creature.isAlive());
    }

    @Test
    void die_WithNullDeathType_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> creature.die(null));
    }
}

