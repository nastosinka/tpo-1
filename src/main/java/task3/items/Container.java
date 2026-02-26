package task3.items;

import lombok.Getter;
import lombok.Setter;
import task3.space.Planet;
import task3.space.SpaceSystem;
import task3.space.Universe;
import task3.types.DeathType;

import java.util.List;

@Setter
@Getter
public class Container {
    private boolean isClosed;
    private List<Item> items;

    public void open() {
        System.out.println("Контейнер открылся");
        this.isClosed = false;
    }

    public void close(SpaceSystem system, Universe universe, Planet targetPlanet) {
        this.isClosed = true;

        for (Item item : items) {
            release(item, system, universe, targetPlanet);
        }

        items.clear();
    }

    public void release(Item item, SpaceSystem system, Universe universe, Planet targetPlanet) {
       item.changeLocation(system);

       if (item instanceof NonLivingEntity entity) {
            if ("Поджаренные яйца".equals(entity.getMaterial())) {
                System.out.println(entity.getQuantity() + " поджаренных яиц материализовались большой кучей на планете " + targetPlanet.getName());
                entity.changeLocation(targetPlanet);
            } else {
                System.out.println("Множество " + entity.getMaterial().toLowerCase() + " устремилось в даль Вселенной...");
                entity.changeLocation(universe);
            }
        } else if (item instanceof Creature creature) {
            System.out.println("Трёхфутовый рыночный аналитик выпал в открытый космос...");
            creature.die(DeathType.CHOKING);
        }
    }
}