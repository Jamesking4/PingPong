package com.jamesking4.game;

import static com.jamesking4.game.PingPong.SCR_HEIGHT;

public class Bot extends Rackets{
    private float x, y;
    private float speedX, speedY;
    private float startY;
    private float width, height;
    private float ballRadius;
    private float ballSpeedY;
    private int hard;
    private boolean comeBack;
    public Bot(float x, float y, float width, float height, int hard) {
        super(x, y, width, height);
        this.hard = hard;
        this.y = y;
        startY = y;
    }

    @Override
    void move(float ballX, float ballY) {
        if (y - ballY < SCR_HEIGHT/4 & y > ballY & y + width < SCR_HEIGHT*97/100 & ballSpeedY > 24) {
            y += 5;
            comeBack = false;
        }
        if (comeBack & y > startY) {
            y -= 10;
            if (y <= startY) {
                comeBack = !comeBack;
            }
        }
        /*if (speedX > 8) {
            if (x + width/2 < ballX + ballRadius/2) {
                x += Math.abs(speedX*47/50);
            } else if (x + width/2 > ballX + ballRadius/2){
                x -= Math.abs(speedX*47/50);
            }
        } else {
            if (x + width/2 < ballX + ballRadius/2) {
                x += Math.abs(speedX) + 2;
            } else if (x + width/2 > ballX + ballRadius/2){
                x -= Math.abs(speedX) - 2;
            }
        }*/
        if (x + width/2 < ballX + ballRadius/2-10) {
            x += 9.5;
        } else if (x + width/2 > ballX + ballRadius/2+10){
            x -= 9.5;
        }

        super.move(x, y);
    }
    void punch() {
        comeBack = true;
    }
    void speed(float ballSpeedX, float ballSpeedY, float ballRadius) {
        this.ballRadius = ballRadius;
        this.ballSpeedY = ballSpeedY;
        speedX = ballSpeedX;
    }
}
