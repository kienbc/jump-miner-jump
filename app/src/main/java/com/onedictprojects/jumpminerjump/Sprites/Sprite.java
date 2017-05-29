package com.onedictprojects.jumpminerjump.Sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.onedictprojects.jumpminerjump.View.GameView;

/**
 * Created by kiencbui on 14/05/2017.
 */

public abstract class Sprite {

    protected Bitmap animation;
    protected int xPos;
    protected int yPos;
    protected float speedX;
    protected float speedY;
    protected int width, height;
    protected Rect src = null;
    protected Rect dst = null;
    protected int currentFrame = 0;
    protected GameView gameView;

    public Sprite() {

    }

    public Sprite(Bitmap animation, GameView gameView) {
        this.animation = animation;
        this.gameView = gameView;
        this.width=animation.getWidth();
        this.height=animation.getHeight();
        this.speedX = 0;
        this.speedY=0;
        this.xPos=0;
        this.yPos=0;
    }

    public void update() {
        xPos+=speedX;
        yPos+=speedY;
    }

    public void draw(Canvas canvas) {
        this.src = new Rect(0,0,this.width,this.height);
        this.dst = new Rect(this.xPos,this.yPos,this.xPos+this.width,this.yPos+this.height);
        canvas.drawBitmap(animation,src,dst,null);
        update();
    }


    public Bitmap getAnimation() {
        return animation;
    }

    public void setAnimation(Bitmap animation) {
        this.animation = animation;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rect getSrc() {
        return src;
    }

    public void setSrc(Rect src) {
        this.src = src;
    }

    public Rect getDst() {
        return dst;
    }

    public void setDst(Rect dst) {
        this.dst = dst;
    }

    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }
}
