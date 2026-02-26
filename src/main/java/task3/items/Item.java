package task3.items;

import lombok.Getter;
import lombok.Setter;
import task3.space.SpaceObject;

@Setter
@Getter
public abstract class Item {

    private Long quantity;
    private SpaceObject location;

    public void changeLocation(SpaceObject location) {
        System.out.println("Сменена локация на " + location.getName());
        this.location = location;
    }
}
