package com.company;

import java.util.TimerTask;

public class MyTimerTask extends TimerTask {

    private final Humanoid humanoid;

    public MyTimerTask(Humanoid humanoid) {
        this.humanoid = humanoid;
    }

    @Override
    public void run() {
        RandomAimlessMovement random_aimless_movement = new RandomAimlessMovement();
        random_aimless_movement.doMovement(humanoid);
    }
}
