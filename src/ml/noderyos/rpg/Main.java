package ml.noderyos.rpg;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        int pv;
        int damage;
        int choix;
        int unpeuttt;
        String trash; // Trash can
        String username;
        Entity mob;
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
        Players.add(new Entity(username, 100, 20));
        for (int j = 0; j < 5; j++){
            pv = random.nextInt(20)+20;
            damage = random.nextInt(15)+15;
            username = mobNames[random.nextInt(5)];
            Mobs.add(new Entity(username,pv,damage));
        }
        while(Players.get(0).getPv() > 0){
            System.out.println("Vous avez " + Players.get(0).getPv() + " PV");
            System.out.println("Que souhaitez vous faire :\n     [1] Se battre\n     [2] Explorer");
            choix = scan.nextInt();
            if(choix == 1){
                unpeuttt = random.nextInt(4) + 1;
                mob = Mobs.get(unpeuttt);
                System.out.println("Vous rencontrez un " + mob.getName() + " avec " + mob.getPv() + " PV et vous enlève " + mob.getDamage() + " PV par coup");
                System.out.println("Que souhaitez vous faire : \n[1] Attaquer le " + mob.getName() + "\n[2] Fuir");
                choix = scan.nextInt();
                if (choix == 1){
                    while (mob.getPv() > 0){
                        mob.setPv(mob.getPv() - Players.get(0).getDamage());
                        System.out.println("Vous infligez " + Players.get(0).getDamage() + " PV au " + mob.getName());
                        TimeUnit.SECONDS.sleep(3);
                        if(mob.getPv() > 0) {
                            System.out.println("Il lui reste " + mob.getPv() + " PV");
                            TimeUnit.SECONDS.sleep(3);
                            Players.get(0).setPv(Players.get(0).getPv() - mob.getDamage());
                            TimeUnit.SECONDS.sleep(3);
                            System.out.println("Le " + mob.getName() + " vous attaque et vous retire " + mob.getDamage() + " PV");
                        }
                    }
                    System.out.println("Votre attaque a été fatale pour lui\nVous gagnez donc ce combat");

                }
                else{
                    System.out.println("Vous pensiez vraiment pouvoir fuir ?");
                    Players.get(0).setPv(0);
                }
            }else {
                if (random.nextInt(4) == 0){
                    unpeuttt = random.nextInt(15) + 15;
                    System.out.println("Vous trouvez une " + lieux[random.nextInt(3) + 3] + " et avez perdu " +unpeuttt + " PV");
                    Players.get(0).setPv(Players.get(0).getPv() - unpeuttt);
                }
                else {
                    unpeuttt = random.nextInt(20) + 20;
                    System.out.println("Vous trouvez une " + lieux[random.nextInt(3)] + " et avez récupéré " + unpeuttt + " PV");
                    Players.get(0).setPv(Players.get(0).getPv() + unpeuttt);
                }
            }
        }
        System.out.println(Players.get(0).getName() + " n'a plus de vie");
    }
}
