package com.example.fishes;

import android.graphics.Point;
import android.os.AsyncTask;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by aptem on 12.09.2017.
 */
public class Game {
    private static RelativeLayout canvas;
    private static Button start;
    private static Button pause;
    private static Button restart;

    private static Engine engine;
    private static boolean isPause;
    private static MyFish myFish;
    private static Oval target;

    public static void start(){
        Fishes.newGame.setContentView(R.layout.canvas);
        getViews();
        getScreenSize();
        setListeners();
        new SoundPool();
        isPause = false;
        //Создание и запуск потока, отвечающего за движение
        engine = new Engine();
        try{
            engine.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } catch (Exception e){}

        target = new Oval();
        target.color = 0xFFFF0000;
        target.size = 15;
        target.location = Listener.location;
    }
    private static void getViews(){
        canvas = Fishes.newGame.findViewById(R.id.canvas);
        start = Fishes.newGame.findViewById(R.id.start);
        pause = Fishes.newGame.findViewById(R.id.pause);
        restart = Fishes.newGame.findViewById(R.id.restart);
    }
    private static void setListeners(){
        canvas.setOnTouchListener(new Listener());
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.removedFishes.addAll(Data.fishes);
                Data.removedEats.addAll(Data.eats);
                addMyFish();
                start.setVisibility(View.INVISIBLE);
                pause.setVisibility(View.VISIBLE);
                restart.setVisibility(View.VISIBLE);
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPause = !isPause;
            }
        });
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPause = false;
                Data.removedFishes.addAll(Data.fishes);
                Data.removedEats.addAll(Data.eats);
                canvas.removeView(target);
                addMyFish();
            }
        });
    }
    private static void getScreenSize(){
        Point sizes = new Point();
        WindowManager w = Fishes.newGame.getWindowManager();
        w.getDefaultDisplay().getSize(sizes);
        Data.width = sizes.x;
        Data.height = sizes.y;
    }
    private static void addMyFish(){
        myFish = new MyFish();
        myFish.color = 0xFFFFFFFF;
        Data.fishes.add(myFish);
        canvas.addView(myFish);
        canvas.addView(target);
    }
    private static class Engine extends AsyncTask<Void, Integer, Void> {
        private static int calc;
        @Override
        protected Void doInBackground(Void... params) {
            while(true){
                while (!isPause) {
                    Util.sleep(10);
                    calc++;
                    publishProgress();
                }
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            just_do_it();
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        private void just_do_it() {
            target.updateGeom();
            start.setTextSize(20 + (float)Math.sin(calc * Math.PI * 0.02));
            if (!Data.fishes.contains(myFish)){
                start.setVisibility(View.VISIBLE);
                pause.setVisibility(View.INVISIBLE);
                restart.setVisibility(View.INVISIBLE);
                canvas.removeView(target);
            }
            if (calc % 50 == 0){
                calc = 0;
                Eat eat = new Eat();
                Data.eats.add(eat);
                canvas.addView(eat);
                eat.updateGeom();
            }
            if (Data.fishes.size() < 5){
                Fish tempFish = new Fish();
                Data.fishes.add(tempFish);
                canvas.addView(tempFish);
            }
            for (Fish fish : Data.fishes) {
                fish.searchTarget();
                fish.integrateSpeed();
                fish.integrateLocation();
                fish.updateGeom();
                fish.checkCollis();
            }
            for (Eat eat : Data.removedEats){
                Game.canvas.removeView(eat);
                Data.eats.remove(eat);
            }
            Data.removedEats.clear();
            for (Fish fish : Data.removedFishes){
                Game.canvas.removeView(fish);
                Data.fishes.remove(fish);
            }
            Data.removedFishes.clear();
        }
    }
}