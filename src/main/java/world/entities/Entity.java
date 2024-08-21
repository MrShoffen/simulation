package world.entities;

public abstract class Entity {
    public int getHealingPower(){
        return (int)(Math.random()*3 + 1);
    }
}
