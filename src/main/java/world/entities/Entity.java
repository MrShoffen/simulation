package world.entities;

public abstract class Entity {
    public int getHealingPower(){
        return (int)(Math.random()*3 + 1);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
