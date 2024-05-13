package com.jamesking4.game;

import static com.jamesking4.game.PingPong.SCR_HEIGHT;
import static com.jamesking4.game.PingPong.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScreenGame implements Screen{
    PingPong pingPong;

    SpriteBatch batch;
    OrthographicCamera camera;
    BitmapFont fontLarge;
    Vector3 touch;

    Balls ball;
    Texture imgBall;

    Texture imgBackGround;
    public ScreenGame(PingPong pingPong) {
        this.pingPong = pingPong;

        batch = pingPong.batch;
        camera = pingPong.camera;
        touch = pingPong.touch;
        fontLarge = pingPong.fontLarge;

        imgBall = new Texture("pictures/ball.png");
        imgBackGround = new Texture("pictures/bgPingPong.png");

        ball = new Balls(SCR_WIDTH*1/20);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ball.move();

        ScreenUtils.clear(1, 0, 0, 1);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        batch.draw(imgBall, ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
