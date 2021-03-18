package ml.noderyos.rpg.entity;

public class Entity {
    String name = "";
    int pv = 0;
    int damage = 0;
    public Entity(String name, int pv, int damage){
        this.name = name;
        this.pv = pv;
        this.damage = damage;
    }
    public void setPv(int pv){ this.pv = pv; }
    public String getName(){ return this.name; }
    public int getPv(){ return this.pv; }
    public int getDamage(){ return this.damage; }
}