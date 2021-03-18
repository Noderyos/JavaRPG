package ml.noderyos.rpg;

import ml.noderyos.rpg.entity.Entity;

public class Main {
    public static void main(String[] args) {
        Entity p1 = new Entity("Noderyos",100,5);
        Entity p2 = new Entity("Courumix",120,10);
        System.out.println(p2.getName() + " a " + p2.getPv() + " PV");
        p2.setPv(p2.getPv() - p1.getDamage());
        System.out.println(p2.getName() + " a " + p2.getPv() + " PV");
    }
}
