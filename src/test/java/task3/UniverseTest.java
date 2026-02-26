package task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task3.space.Planet;
import task3.space.SpaceSystem;
import task3.space.Universe;


import static org.junit.jupiter.api.Assertions.*;

class UniverseTest {

    private Universe universe;
    private Planet planet1;
    private Planet planet2;
    private SpaceSystem system;

    @BeforeEach
    void setUp() {
        universe = new Universe();
        universe.setName("Тестовая вселенная");

        planet1 = new Planet();
        planet1.setName("Меркурий");

        planet2 = new Planet();
        planet2.setName("Венера");

        system = new SpaceSystem();
        system.setName("Солнечная система");

        system.add(planet1);
        system.add(planet2);
        universe.add(system);
    }

    @Test
    void expand_WhenSizeLessThan10000_DoublesSize() {
        universe.setSize(5000.0);
        universe.expand();
        assertEquals(10000.0, universe.getSize());
    }

    @Test
    void expand_WhenSizeEquals10000_NoChange() {
        universe.setSize(10000.0);
        universe.expand();
        assertEquals(10000.0, universe.getSize());
    }

    @Test
    void expand_WhenSizeGreaterThan10000_NoChange() {
        universe.setSize(15000.0);
        universe.expand();
        assertEquals(15000.0, universe.getSize());
    }

    @Test
    void expand_WhenSizeNull_NoChange() {
        universe.setSize(null);
        universe.expand();
        assertNull(universe.getSize());
    }

    @Test
    void expand_MultipleExpands() {
        universe.setSize(2500.0);
        universe.expand();
        universe.expand();
        universe.expand();

        assertEquals(10000.0, universe.getSize());
    }

    @Test
    void findPlanet_ExistingPlanet_ReturnsPlanet() {
        Planet found = universe.findPlanet("Меркурий");
        assertNotNull(found);
        assertEquals("Меркурий", found.getName());
    }

    @Test
    void findPlanet_NonExistingPlanet_ReturnsNull() {
        Planet found = universe.findPlanet("Марс");
        assertNull(found);
    }

    @Test
    void findPlanet_CaseSensitive() {
        Planet found = universe.findPlanet("меркурий");
        assertNull(found);
    }

    @Test
    void findPlanet_WithNullName_ReturnsNull() {
        Planet found = universe.findPlanet("Нататхари");
        assertNull(found);
    }

    @Test
    void findPlanet_EmptyName_ReturnsNull() {
        Planet found = universe.findPlanet("");
        assertNull(found);
    }

    @Test
    void findPlanet_WhenMultiplePlanets() {
        Planet planet3 = new Planet();
        planet3.setName("Меркурий");
        system.add(planet3);

        Planet found = universe.findPlanet("Меркурий");
        assertNotNull(found);
    }
}

