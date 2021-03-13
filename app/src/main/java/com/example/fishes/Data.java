package com.example.fishes;

import java.util.ArrayList;

/**
 * Created by aptem on 19.08.2017.
 */
public class Data {
    public static int width;
    public static int height;
    public static float dt = 1f;

    public static ArrayList<Fish> fishes = new ArrayList<>();
    public static ArrayList<Fish> removedFishes = new ArrayList<>();
    public static ArrayList<Eat> eats = new ArrayList<>();
    public static ArrayList<Eat> removedEats = new ArrayList<>();
}
