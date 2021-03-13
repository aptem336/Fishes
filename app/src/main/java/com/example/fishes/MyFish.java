package com.example.fishes;

/**
 * Created by aptem on 13.09.2017.
 */
public class MyFish extends Fish {
    public void searchTarget() {
        force.set(Vector.getDiff(Listener.location, location));
        force.normalize();
        force.multiply(Data.dt);
    }
}
