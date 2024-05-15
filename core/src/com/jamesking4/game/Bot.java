package com.jamesking4.game;

import static com.jamesking4.game.PingPong.SCR_HEIGHT;
import static com.jamesking4.game.ScreenGame.HEIGHT_TABLE;
import static com.jamesking4.game.ScreenGame.TABLE_X;
import static com.jamesking4.game.ScreenGame.TABLE_Y;
import static com.jamesking4.game.ScreenGame.WIDTH_TABLE;

public class Bot extends Rackets{
    private float x, y;
    private float speedX, speedY;
    private float startY;
    private float width, height;
    private float ballRadius;
    private float maxSpeedX = 8.6f;
    private float ballSpeedY, ballSpeedX;
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

        if (Math.abs(ballSpeedX) > maxSpeedX & y > (TABLE_Y + HEIGHT_TABLE*3.3/4) &
                ((ballX > TABLE_X + WIDTH_TABLE*9/11) & ballSpeedX > 0) | ((ballX < TABLE_X * 13/11)
                & ballSpeedX < 0)) {
            y -= 8;
        } else if (y > ballY & y + height < SCR_HEIGHT*90/100 & ballSpeedY > 40) {
            y += 5;
        } else if (y > startY & !(startY*0.9 < y & y < startY*1.1)) {
            y -= 10;
        } else if (y < startY & !(startY*0.9 < y & y < startY*1.1)) {
            y += 10;
        }
        if (x + width/2 < ballX + ballRadius/2-10) {
            x += maxSpeedX;
        } else if (x + width/2 > ballX + ballRadius/2+10){
            x -= maxSpeedX;
        }

        super.move(x, y);
    }
    void punch() {
        comeBack = true;
    }
    void speed(float ballSpeedX, float ballSpeedY, float ballRadius) {
        this.ballRadius = ballRadius;
        this.ballSpeedY = ballSpeedY;
        this.ballSpeedX = ballSpeedX;
    }
}
