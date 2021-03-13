package com.example.fishes;

/**
 * Created by aptem on 27.08.2017.
 */
public class Fish extends Oval {
    protected Vector speed = new Vector(0, 0);
    protected Vector force = new Vector(0, 0);

    Fish(){
        super();
        size = 35;
        color = 0x44FFFFFF;
    }

    public void searchTarget(){
        double minLenE = 150;
        double minLenF = 200;
        double tempLen;
        force.set(Math.random() - 0.5, Math.random() - 0.5);
        Vector diff;
        for (Eat eat : Data.eats){
            diff = Vector.getDiff(eat.location, location);
            tempLen = diff.len();
            if (tempLen < minLenE){
                minLenE = tempLen;
                force.set(diff);
            }
        }
        for (Fish fish : Data.fishes){
            if (fish != this){
                diff = Vector.getDiff(fish.location, location);
                tempLen = diff.len();
                if (tempLen < minLenF) {
                    minLenF = tempLen;
                    if (size - fish.size > 2) {
                        force.set(diff);
                    }
                    if (fish.size - size > 2) {
                        force.set(diff.getInvert());
                    }
                }
            }
        }
        force.normalize();
        force.multiply(Data.dt);
    }

    public void integrateSpeed(){
        speed.plus(force);
        speed.multiply(1 - 6 * Math.PI * size / 2 * 0.000065);
    }
    public void integrateLocation() {
        location.plus(Vector.getProduct(speed, Data.dt));
        if ((location.x < -50 || location.x > Data.width + 50)&&(location.y < -50 || location.y > Data.height + 50)){
            Data.removedFishes.add(this);
        }
    }

    public void checkCollis(){
        for (Eat eat : Data.eats){
            if (Vector.getDiff(eat.location, location).len() <= (eat.size + size) / 2){
                size += 0.5;
                Data.removedEats.add(eat);
                SoundPool.playSound(1);
            }
        }
        for (Fish fish : Data.fishes){
            if (fish != this) {
                if (Vector.getDiff(fish.location, location).len() <= (fish.size + size) / 2){
                    if (size - fish.size > 2){
                        size += 1;
                        Data.removedFishes.add(fish);
                        SoundPool.playSound(2);
                    }
                }
            }
        }
    }
}
