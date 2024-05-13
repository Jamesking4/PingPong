package com.jamesking4.game;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.jamesking4.game.PingPong.SCR_HEIGHT;
import static com.jamesking4.game.PingPong.SCR_WIDTH;

public class Balls {
    private float x, y;
    private float radius, startRadius;
    private float speedX, speedY, speedZ;

    public Balls(float radius) {
        this.startRadius = radius;
        this.radius = startRadius;
        x = SCR_WIDTH/2 - radius/2;
        y = SCR_HEIGHT/2 - radius/2;
        speedY = random(-15f, -10f);
        speedZ = -startRadius/200;
    }

    void move() {
        x += speedX - speedZ/2;
        y += speedY;
        radius += speedZ;
        if (radius >= startRadius*11.3/10) {
            speedZ = -speedZ;
        }
        if (radius <= startRadius*8.7/10) {
            speedZ = -speedZ;
        }
        if (y > SCR_HEIGHT-radius | y < 0)  {
            speedY = -speedY;
        }
    }

    void reSpawn() {
        x = SCR_WIDTH/2 - radius/2;
        y = SCR_HEIGHT/2 - radius/2;
    }

    float getWidth() {
        return radius;
    }

    float getHeight() {
        return radius;
    }

    float getX() {
        return x;
    }

    float getY() {
        return y;
    }

    float getSpeedX() {
        return speedX;
    }

    float getSpeedY() {
        return speedY;
    }
}
