package task3.items;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NonLivingEntity extends Item {

    private String material;

    String getDescription() {
        return material;
    }

}
