package com.company;
import java.util.Timer;
import java.util.TimerTask;

public class Korotyshka extends Humanoid {

    private Furniture nearest_furniture;

    public class Fist {
        public void bangNearbyFurnitureByFist() {
            BangNearbyFurniture bang_nearby_furniture = new BangNearbyFurniture();
            bang_nearby_furniture.doMovement(Korotyshka.this);
        }
    }

    Korotyshka.Fist fist = this.new Fist();

    public Korotyshka(String name, Furniture furniture) {
        super(name);
        this.nearest_furniture = furniture;
    }

    public Korotyshka(String name, Poses pose, PositionsInRoom pos_in_room) { super(name, pose, pos_in_room); }

    public void getAngry() {
        System.out.printf("%s рассержен!\n", this);
        this.fist.bangNearbyFurnitureByFist();
    }

    public void printRelativePosition() {
        System.out.printf("%s находится у объекта %s с максимальным HP %s.\n", this, this.nearest_furniture,
                Furniture.Parameters.get_max_HP());
    }

    public Furniture getNearbyFurniture() {
        return this.nearest_furniture;
    }

    public void rollOver() {
        System.out.printf("Коротышка %s переворачивается в воздухе.\n", this);
        int pick1 = (int)Math.floor(Math.random() * Poses.values().length);
        while (Poses.values()[pick1] == Poses.ON_THE_GROUND) {
            pick1 = (int)Math.floor(Math.random() * Poses.values().length); //не даю коротышке вернуться на землю
        }
        this.position.setPose(Poses.values()[pick1]);
        int pick2 = (int)Math.floor(Math.random() * PositionsInRoom.values().length);
        while (PositionsInRoom.values()[pick2] == PositionsInRoom.ON_THE_GROUND) {
            pick2 = (int)Math.floor(Math.random() * PositionsInRoom.values().length); //не даю коротышке вернуться на землю
        }
        this.position.setPosition(PositionsInRoom.values()[pick2]);
        System.out.printf("%s принимает позицию %s.\n", this, this.position);
    }
}
