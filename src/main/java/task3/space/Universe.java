package task3.space;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Universe extends SpaceSystem {

    private Long age;
    private Double size;

    public void expand() {
        if (size != null && size < 10000) {
            size *= 2;
        }
    }

    public Planet findPlanet(String name) {
        return findPlanetRecursive(this, name);
    }

    private Planet findPlanetRecursive(SpaceObject obj, String name) {
        if (obj instanceof Planet && name.equals(((Planet) obj).getName())) {
            return (Planet) obj;
        }

        for (SpaceObject child : obj.getChildren()) {
            Planet found = findPlanetRecursive(child, name);
            if (found != null) {
                return found;
            }
        }

        return null;
    }

}
