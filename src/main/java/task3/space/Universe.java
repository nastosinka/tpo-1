package task3.space;

import lombok.Getter;
import lombok.Setter;
import task3.types.StarType;

import java.util.List;

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
        return getChildren().stream()
                .filter(obj -> obj instanceof Planet)
                .map(obj -> (Planet) obj)
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

}
