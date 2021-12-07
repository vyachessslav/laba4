package com.company;

public class Furniture implements MayBeDamaged {

    private final String name;
    private static final int max_HP = 100;
    private int HP = max_HP;

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Furniture other = (Furniture) o;
        return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name == null ? 0 : name.hashCode();
    }

    public static class Parameters {    //если не зайдет, можно сделать static рот
        public static int get_max_HP() {
            return max_HP;
        }
    }

    public class AttackingElement {

        protected String name;

        @Override
        public String toString() {
            return this.name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) { return true; }
            if (o == null || getClass() != o.getClass()) { return false; }
            AttackingElement other = (AttackingElement) o;
            return name.equals(other.name);
        }

        @Override
        public int hashCode() {
            return name == null ? 0 : name.hashCode();
        }

        public void attack(Humanoid humanoid) {
            System.out.printf("%s довольно сильно бьется затылком об %s объекта %s!\n", humanoid, this, Furniture.this);
            humanoid.occiput.damage((int) Math.round(Math.random() * 50));
        }
    }

    Furniture.AttackingElement attacking_element = this.new AttackingElement();

    public Furniture(String name, String name_of_at_el) {
        this.name = name;
        this.attacking_element.name = name_of_at_el;
    }

    public void damage(int hp_lost) {
        this.HP -= hp_lost;
        System.out.printf("%s теряет %sHP!\n", Furniture.this, hp_lost);
    }

}
