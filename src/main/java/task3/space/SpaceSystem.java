package task3.space;


import lombok.Getter;
import lombok.Setter;
import task3.methods.CollapseChildren;
import task3.types.StarType;

@Setter
@Getter
public class SpaceSystem extends SpaceObject implements CollapseChildren {

    private StarType starType;

    @Override
    public void collapse() {
        getChildren().clear();
    }

}
