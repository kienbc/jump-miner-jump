package com.onedictprojects.jumpminerjump.Sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.onedictprojects.jumpminerjump.View.GameView;

/**
 * Created by kiencbui on 14/05/2017.
 */

public class Mine extends Sprite {

    int jumpValue = -300;
    Ground sky;
    Ground ground;

    public Mine(Bitmap animation, GameView gameView) {
        super(animation,gameView);
        setSpeedY(20);
        setxPos(200);
        setyPos(250);
        setWidth(animation.getWidth()/4);
    }

    @Override
    public void update() {
        currentFrame = ++currentFrame % 4;
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(isTouchingSky()) {
            jumpValue = -300;
            speedY = 20;
            yPos = sky.getHeight();
        }

        if (isTouchingGround()) {
            jumpValue = 300;
            speedY = -20;
            yPos = ground.getyPos()-height;
        }
        super.update();
    }

    @Override
    public void draw(Canvas canvas) {
        update();
        int srcX = currentFrame * width;
        this.src = new Rect(srcX,0,srcX + this.width,this.height);
        this.dst = new Rect(this.xPos,this.yPos,this.xPos+this.width,this.yPos+this.height);
        canvas.drawBitmap(animation,src,dst,null);
    }

    public boolean isTouchingSky() {
        return this.yPos < sky.getHeight();
    }

    public boolean isTouchingGround() {
        return (this.yPos+this.height) > ground.yPos;
    }


    public int getJumpValue() {
        return jumpValue;
    }

    public void setJumpValue(int jumpValue) {
        this.jumpValue = jumpValue;
    }

    public Ground getSky() {
        return sky;
    }

    public void setSky(Ground sky) {
        this.sky = sky;
    }

    public Ground getGround() {
        return ground;
    }

    public void setGround(Ground ground) {
        this.ground = ground;
    }
}
