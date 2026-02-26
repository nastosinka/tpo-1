package task3;

import task3.items.Container;
import task3.items.Creature;
import task3.items.NonLivingEntity;

import task3.space.Planet;
import task3.space.SpaceSystem;
import task3.space.Universe;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        Universe universe = new Universe();
        universe.setName("Вселенная");

        SpaceSystem pansel = new SpaceSystem();
        pansel.setName("Пансел");
        universe.add(pansel);

        Planet poghril = new Planet();
        poghril.setName("Погхрил");
        poghril.setCondition("Голод");
        pansel.add(poghril);

        Container container = new Container();
        container.setItems(new ArrayList<>());

        NonLivingEntity hats = new NonLivingEntity();
        hats.setMaterial("бумажные колпаки");
        hats.setQuantity(100L);

        NonLivingEntity balloons = new NonLivingEntity();
        balloons.setMaterial("резиновые шары");
        balloons.setQuantity(50L);

        List<Creature> analysts = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Creature analyst = new Creature();
            analyst.setHeight(3L);
            analyst.setAlive(true);
            analysts.add(analyst);
        }

        NonLivingEntity eggs = new NonLivingEntity();
        eggs.setMaterial("Поджаренные яйца");
        eggs.setQuantity(239000L);

        container.getItems().add(hats);
        container.getItems().add(balloons);
        container.getItems().addAll(analysts);
        container.getItems().add(eggs);

        container.close(pansel, universe, poghril);

        System.out.println("Сценарий завершён.");
    }
}
