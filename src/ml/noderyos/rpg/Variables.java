package ml.noderyos.rpg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Variables {
    int pv;
    int damage;
    int choix;
    boolean inCombat = false;
    Entity mob;
    int unpeuttt;
    int mort = 0;
    String username;
    List<Entity> Players = new ArrayList<>();
    List<Entity> Mobs = new ArrayList<>();
    String[] mobNames = {"Vampire","Sorcier","Zombie","Squelette","Abyssal"};
    String[] lieux = {"Clairière magique","Grotte enchantée","Forêt d'elfes","Grotte d'orques","Zone de sables mouvants","Mare de grenouilles empoisonées"};
    Scanner scan = new Scanner(System.in);
    Random random = new Random();
}
