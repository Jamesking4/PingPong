package com.jamesking4.game;

import static com.jamesking4.game.PingPong.SCR_HEIGHT;
import static com.jamesking4.game.PingPong.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScreenSettings implements Screen {
    PingPong pingPong;

    SpriteBatch batch;
    OrthographicCamera camera;
    Vector3 touch;
    BitmapFont fontLarge;

    Texture imgBackGround;

    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    GlyphLayout layout;

    PongButton btnSound;
    PongButton btnClearRecords;
    PongButton btnBack;

    public ScreenSettings(PingPong pingPong) {
        this.pingPong = pingPong;

        batch = pingPong.batch;
        camera = pingPong.camera;
        touch = pingPong.touch;
        generator = new FreeTypeFontGenerator
                (Gdx.files.internal("fonts/junegull.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) (SCR_HEIGHT*4/100);
        fontLarge = generator.generateFont(parameter);

        imgBackGround = new Texture("pictures/bgPingPong.png");

        btnSound = new PongButton(pingPong.isSoundOn?"Sound On":"Sound Off", SCR_HEIGHT*14/16, SCR_HEIGHT/18, fontLarge);
        btnBack = new PongButton("Back", SCR_HEIGHT*13/16, SCR_HEIGHT/18, fontLarge);

    }

    @Override
    public void show() {
        
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);

            if (btnSound.hit(touch.x, touch.y)) {
                pingPong.isSoundOn = !pingPong.isSoundOn;
                if (pingPong.isSoundOn) {
                    btnSound.setText("Sound On");
                } else {
                    btnSound.setText("Sound Off");
                }
            }
            if (btnBack.hit(touch.x, touch.y)) {
                pingPong.setScreen(pingPong.screenMenu);
            }
        }



        // события игры

        // отрисовка
        ScreenUtils.clear(1, 0, 0, 1);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        btnSound.font.draw(batch, btnSound.text, btnSound.x, btnSound.y);
        btnBack.font.draw(batch, btnBack.text, btnBack.x, btnBack.y);
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
