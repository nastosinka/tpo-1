package task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task3.space.Planet;
import task3.space.SpaceSystem;

import static org.junit.jupiter.api.Assertions.*;

class SpaceSystemTest {

    private SpaceSystem system;
    private Planet planet1;
    private Planet planet2;

    @BeforeEach
    void setUp() {
        system = new SpaceSystem();
        system.setName("Тестовая система");

        planet1 = new Planet();
        planet1.setName("Планета 1");

        planet2 = new Planet();
        planet2.setName("Планета 2");

        system.add(planet1);
        system.add(planet2);
    }

    @Test
    void collapse_ClearsChildren() {
        assertFalse(system.getChildren().isEmpty());

        system.collapse();

        assertTrue(system.getChildren().isEmpty());
    }

    @Test
    void collapse_EmptyChildren_DoesNothing() {
        SpaceSystem emptySystem = new SpaceSystem();
        emptySystem.collapse();

        assertTrue(emptySystem.getChildren().isEmpty());
    }

    @Test
    void collapse_WithMultipleChildren() {
        assertEquals(2, system.getChildren().size());

        system.collapse();

        assertEquals(0, system.getChildren().size());
    }
}
