package task3.space;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public abstract class SpaceObject {

    private String name;
    private List<SpaceObject> children = new ArrayList<>();

    public void add(SpaceObject child) {
        children.add(child);
    }

    public void remove(SpaceObject child) {
        children.remove(child);
    }

}
