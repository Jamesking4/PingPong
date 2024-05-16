package com.jamesking4.game;

import static com.jamesking4.game.PingPong.SCR_HEIGHT;
import static com.jamesking4.game.PingPong.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScreenMenu implements Screen {
    PingPong pingPong;

    SpriteBatch batch;
    OrthographicCamera camera;
    BitmapFont fontLarge;
    Vector3 touch;
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    PongButton btnStart;
    PongButton btnSettings;
    //PongButton btnAbout;
    PongButton btnExit;

    Texture imgBackGround;

    public ScreenMenu(PingPong pingPong) {
        this.pingPong = pingPong;

        batch = pingPong.batch;
        camera = pingPong.camera;
        touch = pingPong.touch;
        generator = new FreeTypeFontGenerator
                (Gdx.files.internal("fonts/junegull.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) (SCR_HEIGHT/24);
        fontLarge = generator.generateFont(parameter);

        btnStart = new PongButton("Start", SCR_HEIGHT*11/16, SCR_HEIGHT/18, fontLarge);
        btnSettings = new PongButton("Settings", SCR_HEIGHT*10/16, SCR_HEIGHT/18, fontLarge);
        //btnAbout = new PongButton("About", SCR_HEIGHT*9/16, SCR_HEIGHT/18, fontLarge);
        btnExit = new PongButton("Exit", SCR_HEIGHT*9/16, SCR_HEIGHT/18, fontLarge);

        imgBackGround = new Texture("pictures/backGroundMenu.png");

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            if(btnStart.hit(touch.x, touch.y)){
                pingPong.setScreen(pingPong.screenGame);
            }
            if(btnSettings.hit(touch.x, touch.y)){
                pingPong.setScreen(pingPong.screenSettings);
            }
            /*if(btnAbout.hit(touch.x, touch.y)){
                pingPong.setScreen(pingPong.screenAbout);
            }*/
            if(btnExit.hit(touch.x, touch.y)){
                Gdx.app.exit();
            }
            touch.setZero();
        }
        
        ScreenUtils.clear(1, 0, 0, 1);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        btnStart.font.draw(batch, btnStart.text, btnStart.x, btnStart.y);
        btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);
        //btnAbout.font.draw(batch, btnAbout.text, btnAbout.x, btnAbout.y);
        btnExit.font.draw(batch, btnExit.text, btnExit.x, btnExit.y);
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
        imgBackGround.dispose();
    }
}
