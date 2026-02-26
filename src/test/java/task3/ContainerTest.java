package task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import task3.items.Container;
import task3.items.Creature;
import task3.items.NonLivingEntity;
import task3.space.Planet;
import task3.space.SpaceSystem;
import task3.space.Universe;
import task3.types.DeathType;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    private Container container;
    private SpaceSystem testSystem;
    private Universe testUniverse;
    private Planet testPlanet;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        container = new Container();
        container.setItems(new ArrayList<>());

        testSystem = new SpaceSystem();
        testSystem.setName("Тестовая система");

        testUniverse = new Universe();
        testUniverse.setName("Тестовая вселенная");

        testPlanet = new Planet();
        testPlanet.setName("Тестовая планета");

        originalOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    void restoreSystemOut() {
        System.setOut(originalOut);
    }

    @Test
    void open_SetsIsClosedToFalse() {
        container.setClosed(true);
        container.open();
        assertFalse(container.isClosed());
    }

    @Test
    void open_PrintsMessage() {
        container.open();
        assertTrue(outputStream.toString().contains("Контейнер открылся"));
    }

    @Test
    void close_SetsIsClosedToTrue() {
        container.close(testSystem, testUniverse, testPlanet);
        assertTrue(container.isClosed());
    }

    @Test
    void close_PrintsNothing() {
        container.close(testSystem, testUniverse, testPlanet);
        assertEquals("", outputStream.toString().trim());
    }

    @Test
    void close_EmptyItemsList_DoesNothing() {
        container.setItems(new ArrayList<>());
        assertDoesNotThrow(() -> container.close(testSystem, testUniverse, testPlanet));
        assertEquals("", outputStream.toString().trim());
    }

    @Test
    void release_NonLivingEggs_ChangesLocationToSystem() {
        NonLivingEntity eggs = new NonLivingEntity();
        eggs.setMaterial("Поджаренные яйца");
        eggs.setQuantity(239000L);

        container.release(eggs, testSystem, testUniverse, testPlanet);

        assertEquals(testPlanet, eggs.getLocation());
    }

    @Test
    void release_NonLivingEggs_ChangesLocationToPlanet() {
        NonLivingEntity eggs = new NonLivingEntity();
        eggs.setMaterial("Поджаренные яйца");
        eggs.setQuantity(239000L);

        container.release(eggs, testSystem, testUniverse, testPlanet);

        assertEquals(testPlanet, eggs.getLocation());
    }

    @Test
    void release_NonLivingEggs_PrintsCorrectMessage() {
        NonLivingEntity eggs = new NonLivingEntity();
        eggs.setMaterial("Поджаренные яйца");
        eggs.setQuantity(239000L);

        container.release(eggs, testSystem, testUniverse, testPlanet);

        String expectedMessage = "239000 поджаренных яиц материализовались большой кучей на планете " + testPlanet.getName();
        assertTrue(outputStream.toString().contains(expectedMessage));
    }

    @Test
    void release_NonLivingEggs_LocationChangeOrder() {
        NonLivingEntity eggs = new NonLivingEntity();
        eggs.setMaterial("Поджаренные яйца");

        container.release(eggs, testSystem, testUniverse, testPlanet);

        String output = outputStream.toString();
        String systemChangeMsg = "Сменена локация на " + testSystem.getName();
        String planetChangeMsg = "Сменена локация на " + testPlanet.getName();

        assertTrue(output.indexOf(systemChangeMsg) < output.indexOf(planetChangeMsg));
    }

    @ParameterizedTest
    @ValueSource(strings = {"бумажные колпаки", "резиновые шары", "металл", "дерево"})
    void release_NonLivingOther_ChangesLocationToSystem(String material) {
        NonLivingEntity item = new NonLivingEntity();
        item.setMaterial(material);
        item.setQuantity(100L);

        container.release(item, testSystem, testUniverse, testPlanet);

        assertEquals(testUniverse, item.getLocation());
    }

    @ParameterizedTest
    @ValueSource(strings = {"бумажные колпаки", "резиновые шары", "металл", "дерево"})
    void release_NonLivingOther_ChangesLocationToUniverse(String material) {
        NonLivingEntity item = new NonLivingEntity();
        item.setMaterial(material);

        container.release(item, testSystem, testUniverse, testPlanet);

        assertEquals(testUniverse, item.getLocation());
    }

    @ParameterizedTest
    @ValueSource(strings = {"бумажные колпаки", "резиновые шары", "металл"})
    void release_NonLivingOther_PrintsCorrectMessage(String material) {
        NonLivingEntity item = new NonLivingEntity();
        item.setMaterial(material);
        item.setQuantity(50L);

        container.release(item, testSystem, testUniverse, testPlanet);

        String expectedMessage = "Множество " + material.toLowerCase() + " устремилось в даль Вселенной...";
        assertTrue(outputStream.toString().contains(expectedMessage));
    }

    @Test
    void release_Creature_Dies() {
        Creature creature = new Creature();
        creature.setAlive(true);
        creature.setHeight(3L);

        container.release(creature, testSystem, testUniverse, testPlanet);

        assertFalse(creature.isAlive());
    }

    @Test
    void release_Creature_DiesWithChokingType() {
        Creature creature = new Creature();

        container.release(creature, testSystem, testUniverse, testPlanet);

        assertEquals(DeathType.CHOKING, creature.getDeathType());
    }

    @Test
    void release_Creature_PrintsMessage() {
        Creature creature = new Creature();
        creature.setHeight(3L);

        container.release(creature, testSystem, testUniverse, testPlanet);

        String output = outputStream.toString();
        assertTrue(output.contains("Трёхфутовый рыночный аналитик выпал в открытый космос..."));
        assertTrue(output.contains("Он погиб от удушья."));
    }

}