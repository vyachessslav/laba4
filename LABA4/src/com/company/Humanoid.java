package com.company;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Humanoid {
    protected final String name;
    public Timer timer;

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Humanoid other = (Humanoid) o;
        return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name == null ? 0 : name.hashCode();
    }

    public Humanoid(String name) {
        this.name = name;
    }

    public Humanoid(String name, Poses pose, PositionsInRoom pos_in_room) {
        this.name = name;
        this.position.setPose(pose);
        this.position.setPosition(pos_in_room);
    }

    public class Occiput implements MayBeDamaged {
        private int HP;

        public Occiput(int hp) {
            this.HP = hp;
        }

        public void damage(int hp_lost) {
            this.HP -= hp_lost;
            System.out.printf("Затылок гуманоида %s теряет %sHP!\n", Humanoid.this, hp_lost);
        }
    }

    Humanoid.Occiput occiput = this.new Occiput(100);

    public class Position {
        private Poses pose;
        private PositionsInRoom pos_in_room;

        @Override
        public String toString() {
            String s1 = "";
            String s2 = "";
            switch (pose) {
                case TILTED: s1 = "диагонально"; break;
                case OBLIQUE: s1 = "косо"; break;
                case ON_THE_GROUND: s1 = "на земле"; break;
                case NORMAL: s1 = "вверх головой"; break;
                case UPSIDE_DOWN: s1 = "вниз головой"; break;
                case HORIZONTALLY: s1 = "горизонтально"; break;
            }
            switch (pos_in_room) {
                case ON_THE_GROUND: s2 = "на земле"; break;
                case UNDER_THE_CEILING: s2 = "под потолком"; break;
                case ON_THE_TOP: s2 = "вверху"; break;
                case AT_THE_BOTTOM: s2 = "внизу"; break;
                case CLOSE_TO_THE_FLOOR: s2 = "недалеко от пола"; break;
            }
            return s1 + " " + s2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) { return true; }
            if (o == null || getClass() != o.getClass()) { return false; }
            Position other = (Position) o;
            if ((pose == other.pose ||
                    pose == Poses.OBLIQUE && other.pose == Poses.TILTED ||
                    pose == Poses.TILTED && other.pose == Poses.OBLIQUE) &&
                    (pos_in_room == other.pos_in_room ||
                            pos_in_room == PositionsInRoom.AT_THE_BOTTOM && other.pos_in_room == PositionsInRoom.CLOSE_TO_THE_FLOOR ||
                            pos_in_room == PositionsInRoom.CLOSE_TO_THE_FLOOR && other.pos_in_room == PositionsInRoom.AT_THE_BOTTOM ||
                            pos_in_room == PositionsInRoom.ON_THE_TOP && other.pos_in_room == PositionsInRoom.UNDER_THE_CEILING ||
                            pos_in_room == PositionsInRoom.UNDER_THE_CEILING && other.pos_in_room == PositionsInRoom.ON_THE_TOP)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            int result = 0;
            switch (pose) {
                case TILTED: result = 1; break;
                case OBLIQUE: result = 1; break;
                case HORIZONTALLY: result = 2; break;
                case NORMAL: result = 3; break;
                case UPSIDE_DOWN: result = 4; break;
                case ON_THE_GROUND: result = 5; break;
            }
            result *= 31;
            switch (pos_in_room) {
                case ON_THE_TOP: result += 1; break;
                case UNDER_THE_CEILING: result += 1; break;
                case AT_THE_BOTTOM: result += 2; break;
                case CLOSE_TO_THE_FLOOR: result += 2; break;
                case ON_THE_GROUND: result += 3; break;
            }
            return result;
        }

        public Position(Poses pose, PositionsInRoom pos_in_room) {
            this.pose = pose;
            this.pos_in_room = pos_in_room;
        }

        public Poses getPose() {
            return this.pose;
        }

        public void setPose(Poses pose) {
            this.pose = pose;
        }

        public PositionsInRoom getPosition() {
            return this.pos_in_room;
        }

        public void setPosition(PositionsInRoom pos_in_room) {
            this.pos_in_room = pos_in_room;
        }
    }

    Position position = new Position(Poses.ON_THE_GROUND, PositionsInRoom.ON_THE_GROUND);

    public abstract void getAngry();

    public abstract void rollOver();

    public void setTimer(int seconds) {
        TimerTask timerTask = new MyTimerTask(this);
        Timer timer = new Timer(true);
        this.timer = timer;
        timer.scheduleAtFixedRate(timerTask, 0, seconds * 1000);
    }

    public void freeze() {
        this.timer.cancel();
        System.out.printf("%s застыл в воздухе в позиции %s.\n", this, this.position);
    }

    public void understandWhatIsRequired() {
        System.out.printf("%s в конце концов понял, что от него требуется.\n", this);
        this.freeze();
    }
}
