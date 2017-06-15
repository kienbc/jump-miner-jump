package com.onedictprojects.jumpminerjump.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.onedictprojects.jumpminerjump.R;
import com.onedictprojects.jumpminerjump.Sprites.Ground;
import com.onedictprojects.jumpminerjump.Sprites.Mine;
import com.onedictprojects.jumpminerjump.Sprites.SmallPillar;
import com.onedictprojects.jumpminerjump.Sprites.Sprite;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Handler;


/**
 * Created by kiencbui on 14/05/2017.
 */

public class GameView extends SurfaceView implements Runnable, View.OnTouchListener {

    Thread thread = null;
    SurfaceHolder holder;
    boolean isItOK = false;
    boolean pillarCreated = false;

    public  static android.os.Handler threadHandler = new android.os.Handler();

    Mine mine = new Mine(BitmapFactory.decodeResource(getResources(), R.drawable.mine_sprite),this);
    Ground groundTop = new Ground(BitmapFactory.decodeResource(getResources(), R.drawable.ground),this);
    Ground groundBottom = new Ground(BitmapFactory.decodeResource(getResources(), R.drawable.ground),this);
    SmallPillar smallPillar = new SmallPillar(BitmapFactory.decodeResource(getResources(),R.drawable.small_pillar),this);

    GameView gameView = null;
    CreatePillarThread createPillarThread = null;

    public static List<Sprite> pillars = null;
    public static boolean isDead = false;
    public static boolean isInit = false;
    public static int score = 0;

    public GameView(Context context) {
        super(context);

        gameView = this;

        pillars = new LinkedList<>();

        holder=getHolder();
        mine.setyPos(groundTop.getHeight());
        mine.setGround(groundBottom);
        mine.setSky(groundTop);
    }

    @Override
    public void run() {
        while (isItOK && !isDead) {
            if(!holder.getSurface().isValid()) {
                continue;
            }

            if(!pillarCreated) {
                //create a thread to create all pillars
                createPillarThread = new CreatePillarThread();
                threadHandler.postDelayed(createPillarThread,2200);

                pillarCreated = true;
            }

            Canvas canvas = holder.lockCanvas();
            draw(canvas);
            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void pause() {
        isItOK=false;
        while (true) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }
        thread = null;
    }

    public void resume() {
        isItOK=true;
        thread= new Thread(this);
        thread.start();
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawARGB(255,255,255,255);

        if(!isInit) {
            gameView=this;
            groundBottom.setyPos(this.getHeight()-groundBottom.getHeight());
            isInit=true;
        }

        mine.draw(canvas);
        groundTop.draw(canvas);
        groundBottom.draw(canvas);

        // draw all pillars from list
        for(int i=0;i<pillars.size();i++)
            pillars.get(i).draw(canvas);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                    mine.setyPos(mine.getyPos()+mine.getJumpValue());
                break;
        }
        return true;
    }

    class CreatePillarThread implements Runnable {
        public void run() {
            int posY = randomYPos(700,gameView.getHeight()-400);

            SmallPillar pillar1 = new SmallPillar(BitmapFactory.decodeResource(getResources(),R.drawable.small_pillar),gameView);
            pillar1.setxPos(gameView.getWidth());
            pillar1.setyPos(posY);
            pillar1.setMiner(mine);
            pillar1.setRunnable(this);
            pillars.add(pillar1);

            SmallPillar pillar2 = new SmallPillar(BitmapFactory.decodeResource(getResources(),R.drawable.small_pillar),gameView);
            pillar2.setxPos(gameView.getWidth());
            pillar2.setyPos(posY-gameView.getHeight());
            pillar2.setMiner(mine);
            pillar2.setRunnable(this);
            pillars.add(pillar2);

            threadHandler.postDelayed(this,2200);
        }
    }

    public static int randomYPos(int min, int max) {
        Random rand = new Random();

        return  rand.nextInt(max-min+1)+min;
    }

}
