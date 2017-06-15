package com.onedictprojects.jumpminerjump.Sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.onedictprojects.jumpminerjump.View.GameView;

/**
 * Created by kiencbui on 15/05/2017.
 */

public class SmallPillar extends Sprite {

    Mine miner;

    Runnable runnable;

    public SmallPillar(Bitmap animation, GameView gameView) {
        super(animation,gameView);
        setSpeedX(-15);
        setxPos(gameView.getWidth());
    }

    @Override
    public void update() {
        if(isTouchingMiner()) {
            GameView.isDead=true;
            GameView.threadHandler.removeCallbacks(runnable);
        }

        if(isOverMiner())
            GameView.score+=1;

        if(isOverScreen())
            GameView.pillars.remove(this);


        super.update();
    }

    public boolean isTouchingMiner() {
        if(miner.getyPos() < yPos+height && miner.getyPos()+miner.getHeight() > yPos && miner.xPos+miner.getWidth() > xPos && miner.getxPos() < xPos+width)
            return true;

        return false;
    }

    public boolean isOverMiner() {
        if(miner.getxPos() + miner.getWidth()/2 >= xPos+width/2)
            return true;
        return false;
    }

    public boolean isOverScreen() {
        if(xPos + width < 0)
            return true;
        return false;
    }

    public Mine getMiner() {
        return miner;
    }

    public void setMiner(Mine miner) {
        this.miner = miner;
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }
}
