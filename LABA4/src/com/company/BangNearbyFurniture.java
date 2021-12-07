package com.company;

public class BangNearbyFurniture implements AbruptMovement, Movement {

    public void doMovement(Humanoid humanoid) {
        if (humanoid instanceof Korotyshka) {
            Furniture furniture = ((Korotyshka)humanoid).getNearbyFurniture();
            System.out.printf("%s бьет кулаком по объекту %s!\n", humanoid, furniture);
            furniture.damage((int) Math.round(Math.random() * 30));
            this.badConsequence(humanoid, furniture);
        }
    }

    public void badConsequence(Humanoid humanoid, Object object) {
        if (object instanceof Furniture) {
            humanoid.rollOver();
            ((Furniture) object).attacking_element.attack(humanoid);
        }
    }
}
