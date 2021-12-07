package com.company;

public class RandomAimlessMovement implements Movement {
    public void doMovement(Humanoid humanoid) {
        System.out.printf("%s делает бессмысленное движение.\n", humanoid);
        if (humanoid.position.getPose() != Poses.ON_THE_GROUND) {
            humanoid.rollOver();
        }
    }
}
