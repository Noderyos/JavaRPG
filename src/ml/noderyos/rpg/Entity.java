package ml.noderyos.rpg;

import java.util.ArrayList;
import java.util.List;

public class Entity {
    String name;
    int pv;
    int damage;
    boolean isPlayer;
    String[] listItems = {"Potion de Vie","Potion de Poison"};
    List<String> possibleItems = new ArrayList<>();
    List<String> inventory = new ArrayList<>();
    public Entity(String name, int pv, int damage, boolean isPlayer){
        this.name = name;
        this.pv = pv;
        this.damage = damage;
        if(isPlayer){
            for (String itemName : listItems){
                possibleItems.add(itemName);
            }
        }
    }
    public void setPv(int pv){ this.pv = pv; }
    public String getName(){ return this.name; }
    public int getPv(){ return this.pv; }
    public int getDamage(){ return this.damage; }
    public String getInventoryItemNameById(int id){
        return inventory.get(id);
    }
    public int getInventoryItemIdByName(String name){
        return inventory.indexOf(name);
    }
    public void addInventoryItem(int id){
        inventory.add(possibleItems.get(id));
    }
    public void removeInventoryItem(int id){
        inventory.remove(possibleItems.get(id));
    }
    public String getInventory(){
        String list = "";
        for(String item : inventory){
            list += (item + "\n");
        }
        return list;
    }
}