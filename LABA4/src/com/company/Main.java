package com.company;

import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void wait1(double secs) {
        try {
            Thread.sleep((int)Math.ceil(secs * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        final int seconds = 2;
        final int time1 = 4;
        final int time2 = 2;

        Furniture table = new Furniture("стол", "угол");
        Korotyshka znayka = new Korotyshka("Знайка", table);
        Korotyshka neznayka = new Korotyshka("Незнайка", Poses.UPSIDE_DOWN, PositionsInRoom.CLOSE_TO_THE_FLOOR );
        Korotyshka vintik = new Korotyshka("Винтик", Poses.HORIZONTALLY, PositionsInRoom.AT_THE_BOTTOM);
        Korotyshka shpuntik = new Korotyshka("Шпунтик", Poses.OBLIQUE, PositionsInRoom.UNDER_THE_CEILING);
        Korotyshka pilulkin = new Korotyshka("Пилюлькин", Poses.NORMAL, PositionsInRoom.UNDER_THE_CEILING);
        Korotyshka ponchik = new Korotyshka("Пончик", Poses.TILTED, PositionsInRoom.ON_THE_TOP);
        Korotyshka[] korotyshkas = {neznayka, vintik, shpuntik, pilulkin, ponchik};

        System.out.println("--------------------");
        znayka.printRelativePosition();
        System.out.println("--------------------");

        wait1(1.5);

        for (Korotyshka korotyshka: korotyshkas) {
            korotyshka.setTimer(seconds);
        }

        wait1(time1 - 0.1);

        for (Korotyshka korotyshka: korotyshkas) {
            korotyshka.timer.cancel();
        }

        wait1(0.1);

        System.out.println("--------------------");
        znayka.getAngry();
        System.out.println("--------------------");

        wait1(2);

        for (Korotyshka korotyshka: korotyshkas) {
            korotyshka.setTimer(seconds);
        }

        wait1(time2 - 0.1);

        System.out.println("--------------------");

        for (Korotyshka korotyshka: korotyshkas) {
            korotyshka.understandWhatIsRequired();
        }
    }
}
