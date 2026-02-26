package task3.space;

import lombok.Getter;
import lombok.Setter;
import task3.types.PlanetType;

@Getter
@Setter
public class Planet extends SpaceObject {

    private String condition;
    private Long population;
    private Boolean pole;
    private PlanetType type;

    void changePole() {
        if (pole != null) {
            this.pole = !this.pole;
        }
    }
}
