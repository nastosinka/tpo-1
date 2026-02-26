package task3.items;

import lombok.Getter;
import lombok.Setter;
import task3.types.DeathType;

@Setter
@Getter
public class Creature extends Item {

    private Long height;
    private boolean isAlive;
    private DeathType deathType;

    public void die(DeathType deathType) {
        if (deathType == DeathType.CHOKING) {
            System.out.println("Он погиб от удушья.");
        } else if (deathType == DeathType.SURPRISE) {
            System.out.println("Он умер от удивления.");
        }
    }

}
