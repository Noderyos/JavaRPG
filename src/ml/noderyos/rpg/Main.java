package ml.noderyos.rpg;

import java.util.*;
import java.util.concurrent.TimeUnit;
import club.minnced.discord.rpc.*;
public class Main {

    public static void main(String[] args) throws InterruptedException{
        Variables var  = new Variables();
        discordRPC();
        game(var);
    }
    //TODO : Game Start
    public static void game(Variables var) throws InterruptedException{
        // TODO : Creating User
        System.out.println("\nPseudo :");
        var.username = var.scan.nextLine();
        var.Players.add(new Entity(var.username, 100, 20,true));
        for (int j = 0; j < 50; j++){
            var.pv = var.random.nextInt(20) + 20;
            var.damage = var.random.nextInt(15)+15;
            var.username = var.mobNames[var.random.nextInt(5)];
            var.Mobs.add(new Entity(var.username,var.pv,var.damage,false));
        }
        while(var.Players.get(0).getPv() > 0 && var.mort < 5){
            mainChoice(var);
        }
        if (var.Players.get(0).getPv() > 0){
            System.out.println(var.Players.get(0).getName() + " a remporté la partie en tuant 5 monstres");
        }else{
            System.out.println(var.Players.get(0).getName() + " n'a plus de PV");
        }
    }
    //TODO : Main Menu
    public static void mainChoice(Variables var) throws InterruptedException{
        var.mob = var.Mobs.get(0);
        System.out.println("Vous avez " + var.Players.get(0).getPv() + " PV");
        System.out.println("Que souhaitez vous faire :\n     [1] Se battre\n     [2] Explorer");
        var.choix = var.scan.nextInt();
        if(var.choix == 1){
            fight(var);
        }else {
            explore(var);
        }
    }
    //TODO : Explore
    public static void explore(Variables var){
        int unpeuttt;
        if (var.random.nextInt(2) == 0){
            unpeuttt = var.random.nextInt(20) + 15;
            System.out.println("Vous trouvez une " + var.lieux[var.random.nextInt(3) + 3] + " et avez perdu " +unpeuttt + " PV");
            var.Players.get(0).setPv(var.Players.get(0).getPv() - unpeuttt);
        }
        else {
            unpeuttt = var.random.nextInt(20) + 20;
            System.out.println("Vous trouvez une " + var.lieux[var.random.nextInt(3)] + " et avez récupéré " + unpeuttt + " PV");
            var.Players.get(0).setPv(var.Players.get(0).getPv() + unpeuttt);
        }
    }
    //TODO : Fight
    public static void fight(Variables var) throws InterruptedException{
        int choix;
        int unpeuttt;
        Random random = new Random();
        if(!var.inCombat){
            unpeuttt = random.nextInt(var.Mobs.size() - 1);
            var.mob = var.Mobs.get(unpeuttt);
            System.out.println("Vous rencontrez un " + var.mob.getName() + " avec " + var.mob.getPv() + " PV et vous enlève " + var.mob.getDamage() + " PV par coup");
        }
        var.inCombat = true;
        System.out.println("Que souhaitez vous faire : \n[1] Attaquer le " + var.mob.getName() + "\n[2] Utiliser une potion\n[3] Fuir");
        choix = var.scan.nextInt();
        if (choix == 1){
            if(var.mob.getPv() > 0){
                attack(var);
            }else{
                System.out.println("Votre attaque a été fatale pour lui\n Vous gagnez donc ce combat");
                var.Mobs.remove(var.mob);
                if(var.random.nextInt(4) == 3){
                    var.unpeuttt = random.nextInt(2);
                    var.Players.get(0).addInventoryItem(var.unpeuttt);
                    System.out.println("Vous lui faites les poches et trouvez une " + var.Players.get(0).getInventoryItemNameById(var.unpeuttt));
                }
                System.out.println("Il ne portait pas sur lui d'objets sur lui");
            }
        }
        else if (choix == 2){
            inventory(var);
        }
        else{
            escape(var);
        }
    }
    //TODO : Attack
    public static void attack(Variables var) throws InterruptedException{
        var.mob.setPv(var.mob.getPv() - var.Players.get(0).getDamage());
        System.out.println("Vous infligez " + var.Players.get(0).getDamage() + " PV au " + var.mob.getName());
        TimeUnit.SECONDS.sleep(1);
        if(var.mob.getPv() > 0) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Il lui reste " + var.mob.getPv() + " PV");
            TimeUnit.SECONDS.sleep(1);
            var.Players.get(0).setPv(var.Players.get(0).getPv() - var.mob.getDamage());
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Le " + var.mob.getName() + " vous attaque et vous retire " + var.mob.getDamage() + " PV");
        }
    }
    //TODO : Escape
    public static void escape(Variables var){
        System.out.println("Vous pensiez vraiment pouvoir fuir ?");
        var.Players.get(0).setPv(0);
    }
    //TODO : Inventory
    public static void inventory(Variables var) throws InterruptedException{
        System.out.println("Vous avez : \n" + var.Players.get(0).getInventory());
        System.out.println("Que souhaites-vous faire : \n[1] Utiliser\n[2] Quitter l'inventaire");
        int choix = var.scan.nextInt();
        if(choix == 1){
            String trash = var.scan.nextLine();
            System.out.println("Quel item souhaitez vous utiliser ?");
            String choix2 = var.scan.nextLine();
            if(choix2.equals("Potion de Vie")){
                var.Players.get(0).setPv(var.Players.get(0).getPv() + 20);
                var.Players.get(0).removeInventoryItem(var.Players.get(0).getInventoryItemIdByName("Potion de Vie"));
            }else if(choix2.equals("Potion de Poison")){
                var.mob.setPv(var.mob.getPv() - 10);
                var.Players.get(0).removeInventoryItem(var.Players.get(0).getInventoryItemIdByName("Potion de Poison"));
            }else{
                System.out.println("Vous ne possedez pas cet item");
            }
        }else{
            fight(var);
        }
    }
    //TODO : Discord RPC
    public static void discordRPC(){
        DiscordRPC lib = DiscordRPC.INSTANCE;
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        lib.Discord_Initialize("826877975836098611", handlers, true, "");
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.details = "by Noderyos";
        presence.largeImageKey = "rpg";
        lib.Discord_UpdatePresence(presence);
    }
}