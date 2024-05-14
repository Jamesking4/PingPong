package com.jamesking4.game;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.jamesking4.game.PingPong.SCR_HEIGHT;
import static com.jamesking4.game.PingPong.SCR_WIDTH;

import com.badlogic.gdx.math.Vector2;

public class Rackets {
    private float x, y;
    private float width, height;
    private float deltaX, deltaY;
    private float lastX, lastY;
    private int ticrate;


    public Rackets(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    void move(float x, float y) {
        ticrate += 1;
        this.x = x - width/2;
        this.y = y - height/2;
        if (ticrate >= 2) {
            lastX = this.x;
            lastY = this.y;
            ticrate = 0;
        }
        deltaX = this.x - lastX;
        deltaY = this.y - lastY;

    }

    float getWidth() {
        return width;
    }

    float getHeight() {
        return height;
    }

    float getX() {
        return x;
    }

    float getY() { return y; }

    float getSpeedX() {
        return deltaX;
    }

    float getSpeedY() {
        return deltaY;
    }

}
