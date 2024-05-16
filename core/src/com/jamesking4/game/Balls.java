package com.jamesking4.game;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.jamesking4.game.PingPong.SCR_HEIGHT;
import static com.jamesking4.game.PingPong.SCR_WIDTH;
import static com.jamesking4.game.ScreenGame.HEIGHT_TABLE;
import static com.jamesking4.game.ScreenGame.TABLE_X;
import static com.jamesking4.game.ScreenGame.TABLE_Y;
import static com.jamesking4.game.ScreenGame.WIDTH_TABLE;
import static com.jamesking4.game.ScreenGame.scoreBot;
import static com.jamesking4.game.ScreenGame.scorePlayer;

public class Balls {
    private float x, y;
    private float radius, startRadius;
    private float speedX, speedY, speedZ;
    private float maxSpeedX = 10, maxSpeedY = 45;
    private int spz = 50;
    private float minZ, maxZ;
    
    public Balls(float radius) {
        this.startRadius = radius;
        this.radius = startRadius;
        reSpawn();
        minZ = startRadius*8/10;
        maxZ = startRadius*13/10;
    }

    void move() {
        x += speedX - speedZ/2;
        y += speedY;
        radius += speedZ;
        if (radius >= maxZ) {
            speedZ = -startRadius/spz;
        }
        if (radius <= minZ) {
            speedZ = startRadius/spz;
        }
        score();

    }

    void reSpawn() {
        x = SCR_WIDTH/2 - radius/2;
        y = (TABLE_Y + HEIGHT_TABLE*1/10) - radius/2;
        speedX = 0;
        speedY = 0;
        speedZ = 0;
    }


    float getStartRadius() {
        return startRadius;
    }

    float getRadius() {
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
    boolean punch(float racketX, float racketY, float racketWidth, float racketHeight, float speedRX, float speedRY, int numPlayer) {
        if ((y < racketY + racketHeight & y + startRadius > racketY) & (x + startRadius > racketX & x < racketX + racketWidth)) {
            if ((speedY <= 0 & numPlayer == 0) & y < (TABLE_Y + HEIGHT_TABLE*1/4) & (speedRY) >= 0) {
                speedY = -speedY;
                speedX += speedRX;
                speedY += speedRY*2;
                if (speedZ <= 0) {
                    speedZ = startRadius/spz;
                }
                radius = maxZ;
                autoGuidance();
                maxSpeed();
                return true;
            } else if (speedY >= 0 & numPlayer == 1) {
                speedY = -speedY;
                speedX += speedRX;
                speedY -= 7;
                if (speedZ <= 0) {
                    speedZ = startRadius/spz;
                }
                radius = maxZ;
                autoGuidanceBot();
                maxSpeed();
                return true;
            }
        }
        return false;
    }
    void maxSpeed() {
        if (speedY > maxSpeedY) {
            speedY = maxSpeedY;
        } else if (speedY < -maxSpeedY) {
            speedY = -maxSpeedY;
        }
        if (speedX > maxSpeedX) {
            speedX = maxSpeedX;
        } else if (speedX < -maxSpeedX) {
            speedX = -maxSpeedX;
        }
    }
    void autoGuidance() {
        float time = (radius-minZ)/Math.abs(speedZ);
        for (int i = 0; i < 2; i++) {
            while (x + speedX * time > (TABLE_X + WIDTH_TABLE) * 20 / 21) {
                speedX -= 1;
            }
            while (x + speedX * time < TABLE_X * 21 / 20) {
                speedX += 1;
            }
            while (y + speedY * time > (TABLE_Y + HEIGHT_TABLE) * 20 / 21) {
                speedY -= 1;
            }
            while (y + speedY * time < (TABLE_Y + HEIGHT_TABLE*2.6/4)) {
                speedY += 1;
            }
        }
    }
    void autoGuidanceBot() {
        float time = (radius-minZ)/ Math.abs(speedZ);
        for (int i = 0; i < 2; i++) {
            while (x+speedX*time < (TABLE_X)*21/20) {
                speedX += 1;
            }
            while (x+speedX*time > (TABLE_X + WIDTH_TABLE)*20/21) {
                speedX -= 1;
            }
            while (y+speedY*time < TABLE_Y*21/20) {
                speedY += 1;
            }
            while (y+speedY*time > (TABLE_Y + HEIGHT_TABLE* 1/4)) {
                speedY -= 1;
            }
        }
    }
    void score(){
        if (outOfScreen()) {
            if (speedY < 0) {
                scoreBot += 1;
                reSpawn();
            } else {
                scorePlayer += 1;
                reSpawn();
            }
        }
    }

    boolean punchOnTable() {
        if (radius <= minZ) return true;
        return false;
    }
    boolean ballOnTable() {
        if ((x < TABLE_X + WIDTH_TABLE) & x > TABLE_X & y > TABLE_Y & (y < TABLE_Y + HEIGHT_TABLE)) {
            return true;
        }
        return false;
    }
    boolean outOfScreen() {
        if (((y > SCR_HEIGHT-radius) | (y < 0)) | ((x < 0) | (x + radius > SCR_WIDTH)))  {
            return true;
        }
        return false;
    }
}
