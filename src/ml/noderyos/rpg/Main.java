package ml.noderyos.rpg;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        game();
    }
    public static void game() throws InterruptedException{
        int pv;
        int damage;
        int choix;
        int unpeuttt;
        int mort = 0;
        String username;

        List<Entity> Players = new ArrayList<>();
        List<Entity> Mobs = new ArrayList<>();
        String[] mobNames = {"Vampire","Sorcier","Zombie","Squelette","Abyssal"};
        String[] lieux = {"Clairière magique","Grotte enchantée","Forêt d'elfes","Grotte d'orques","Zone de sables mouvants","Mare de grenouilles empoisonées"};
        Scanner scan = new Scanner(System.in);
        Random random = new Random();
        //########################
        //#    Creating Users    #
        //########################
        System.out.println("\nPseudo :");
        username = scan.nextLine();
        Players.add(new Entity(username, 100, 20,true));
        for (int j = 0; j < 50; j++){
            pv = random.nextInt(20)+20;
            damage = random.nextInt(15)+15;
            username = mobNames[random.nextInt(5)];
            Mobs.add(new Entity(username,pv,damage,false));
        }
        while(Players.get(0).getPv() > 0 && mort < 5){
            mainChoice(random,Players,Mobs,mort,scan,lieux);
        }
        if (Players.get(0).getPv() > 0){
            System.out.println(Players.get(0).getName() + " a remporté la partie en tuant 5 monstres");
        }else{
            System.out.println(Players.get(0).getName() + " n'a plus de PV");
        }
    }
    public static void mainChoice(Random random, List<Entity> Players,List<Entity> Mobs,int mort,Scanner scan,String[] lieux) throws InterruptedException{
        int choix;
        int unpeuttt;
        boolean inCombat = false;
        Entity mob;
        mob = Mobs.get(0);
        System.out.println("Vous avez " + Players.get(0).getPv() + " PV");
        System.out.println("Que souhaitez vous faire :\n     [1] Se battre\n     [2] Explorer");
        choix = scan.nextInt();
        if(choix == 1){
            fight(Players,Mobs,mort,scan,inCombat,mob);
        }else {
            explore(random,Players,lieux);
        }
    }
    public static void explore(Random random,List<Entity> Players,String[] lieux){
        int choix;
        int unpeuttt;
        if (random.nextInt(2) == 0){
            unpeuttt = random.nextInt(20) + 15;
            System.out.println("Vous trouvez une " + lieux[random.nextInt(3) + 3] + " et avez perdu " +unpeuttt + " PV");
            Players.get(0).setPv(Players.get(0).getPv() - unpeuttt);
        }
        else {
            unpeuttt = random.nextInt(20) + 20;
            System.out.println("Vous trouvez une " + lieux[random.nextInt(3)] + " et avez récupéré " + unpeuttt + " PV");
            Players.get(0).setPv(Players.get(0).getPv() + unpeuttt);
        }
    }
    public static void fight(List<Entity> Players,List<Entity> Mobs,int mort,Scanner scan,boolean inCombat,Entity mob) throws InterruptedException{
        int choix;
        int unpeuttt;
        Random random = new Random();
        if(!inCombat){
            unpeuttt = random.nextInt(Mobs.size() - 1);
            mob = Mobs.get(unpeuttt);
            System.out.println("Vous rencontrez un " + mob.getName() + " avec " + mob.getPv() + " PV et vous enlève " + mob.getDamage() + " PV par coup");
        }
        inCombat = true;
        System.out.println("Que souhaitez vous faire : \n[1] Attaquer le " + mob.getName() + "\n[2] Utiliser une potion\n[3] Fuir");
        choix = scan.nextInt();
        if (choix == 1){
            attack(Players,Mobs,mort,mob,inCombat);
        }
        else if (choix == 2){
            inventory(Players,Mobs,mort,mob,scan,inCombat);
        }
        else{
            escape(Players);
        }
    }
    public static void attack(List<Entity> Players,List<Entity> Mobs,int mort,Entity mob,boolean inCombat) throws InterruptedException{
        Random random = new Random();
        int choix;
        int unpeuttt;
        while (mob.getPv() > 0){
            mob.setPv(mob.getPv() - Players.get(0).getDamage());
            System.out.println("Vous infligez " + Players.get(0).getDamage() + " PV au " + mob.getName());
            TimeUnit.SECONDS.sleep(1);
            if(mob.getPv() > 0) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Il lui reste " + mob.getPv() + " PV");
                TimeUnit.SECONDS.sleep(1);
                Players.get(0).setPv(Players.get(0).getPv() - mob.getDamage());
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Le " + mob.getName() + " vous attaque et vous retire " + mob.getDamage() + " PV");
            }
        }
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Votre attaque a été fatale pour lui\nVous gagnez donc ce combat\n");
        mort += 1;
        inCombat = false;
        unpeuttt = random.nextInt(2);
        if (random.nextInt(4) == 0){
            Players.get(0).addInventoryItem(unpeuttt);
            System.out.println("Vous avez obtenu une Potion");
        }
        Mobs.remove(unpeuttt);
    }
    public static void escape(List<Entity> Players){
        int choix;
        int unpeuttt;
        System.out.println("Vous pensiez vraiment pouvoir fuir ?");
        Players.get(0).setPv(0);
    }
    public static void inventory(List<Entity> Players,List<Entity> Mobs,int mort,Entity mob,Scanner scan, boolean inCombat) throws InterruptedException{
        System.out.println("Vous avez : \n" + Players.get(0).getInventory());
        fight(Players,Mobs,mort,scan,inCombat,mob);
    }
}
