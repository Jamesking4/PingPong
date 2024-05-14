package com.jamesking4.game;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.jamesking4.game.PingPong.SCR_HEIGHT;
import static com.jamesking4.game.PingPong.SCR_WIDTH;
import static com.jamesking4.game.ScreenGame.HEIGHT_TABLE;
import static com.jamesking4.game.ScreenGame.TABLE_X;
import static com.jamesking4.game.ScreenGame.TABLE_Y;
import static com.jamesking4.game.ScreenGame.WIDTH_TABLE;

public class Balls {
    private float x, y;
    private float radius, startRadius;
    private float speedX, speedY, speedZ;
    private int spz = 50;
    private float minZ, maxZ;
    
    public Balls(float radius) {
        this.startRadius = radius;
        this.radius = startRadius;
        reSpawn();
        speedY = random(-15f, -10f);
        speedZ = -startRadius/spz;
        minZ = startRadius*8/10;
        maxZ = startRadius*13/10;
    }

    void move() {
        x += speedX - speedZ/2;
        y += speedY;
        if ((y > SCR_HEIGHT-radius & speedY > 0) | (y < 0 & speedY < 0))  {
            speedY = -speedY;
            speedY = speedY*9/10;
        }
        if ((x < 0 & speedX < 0) | (x + radius > SCR_WIDTH & speedX > 0)) {
            speedX = -speedX;
            speedX = speedX*9/10;
        }
        radius += speedZ;
        if (radius >= maxZ) {
            speedZ = -startRadius/spz;
        }
        if (radius <= minZ) {
            speedZ = startRadius/spz;
        }
    }

    void reSpawn() {
        x = SCR_WIDTH/2 - radius/2;
        y = SCR_HEIGHT/2 - radius/2;
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
            if (speedY < 0 & numPlayer == 0) {
                speedY = -speedY;
                speedX += speedRX*9/10;
                speedY += speedRY*2;
                if (speedZ < 0) {
                    speedZ = startRadius/spz;
                }
                radius = maxZ;
                autoGuidance();
                maxSpeed();
            } else if (speedY > 0 & numPlayer == 1) {
                speedY = -speedY;
                speedX += speedRX*9/10;
                speedY -= 7;
                if (speedZ < 0) {
                    speedZ = startRadius/spz;
                }
                radius = maxZ;
                autoGuidanceBot();
                maxSpeed();
            }
            return true;
        }
        return false;
    }
    void maxSpeed() {
        if (speedY > 35) {
            speedY = 35;
        } else if (speedY < -35) {
            speedY = -35;
        }
        if (speedX > 10) {
            speedX = 10;
        } else if (speedX < -10) {
            speedX = -10;
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
            while (y + speedY * time < SCR_HEIGHT / 2 * 21 / 19) {
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
            while (y+speedY*time > SCR_HEIGHT/2*19/21) {
                speedY -= 1;
            }
        }
    }
}
