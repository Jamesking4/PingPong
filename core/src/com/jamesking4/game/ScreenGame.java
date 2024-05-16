package com.jamesking4.game;

import static com.jamesking4.game.PingPong.SCR_HEIGHT;
import static com.jamesking4.game.PingPong.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import org.w3c.dom.Text;

public class ScreenGame implements Screen{
    public static final float WIDTH_TABLE = SCR_WIDTH*13/20;
    public static final float HEIGHT_TABLE = SCR_WIDTH*13/20*9/5;
    public static final float TABLE_X = SCR_WIDTH/2 - WIDTH_TABLE/2;
    public static final float TABLE_Y = SCR_HEIGHT/2-HEIGHT_TABLE/2;
    public static int scoreBot, scorePlayer;
    PingPong pingPong;

    SpriteBatch batch;
    OrthographicCamera camera;
    BitmapFont fontLarge;
    Vector3 touch;
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    GlyphLayout layout;

    Sound sndPunchOnTable;
    Sound sndPunchOnRacket;

    Balls ball;
    Rackets racket;
    Bot bot;

    Texture imgBall;
    Texture imgBlock;
    Texture imgBackGround;
    Texture imgTable;
    public ScreenGame(PingPong pingPong) {
        this.pingPong = pingPong;

        batch = pingPong.batch;
        camera = pingPong.camera;
        touch = pingPong.touch;
        generator = new FreeTypeFontGenerator
                (Gdx.files.internal("fonts/junegull.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) (SCR_HEIGHT*2/100);
        fontLarge = generator.generateFont(parameter);

        imgBall = new Texture("pictures/ball.png");
        imgBackGround = new Texture("pictures/bgPingPong.png");
        imgBlock = new Texture("pictures/block.png");
        imgTable = new Texture("pictures/table.png");

        sndPunchOnTable = Gdx.audio.newSound(Gdx.files.internal("sounds/punchOnTable.mp3"));
        sndPunchOnRacket = Gdx.audio.newSound(Gdx.files.internal("sounds/punchOnRacket.mp3"));

        ball = new Balls(SCR_WIDTH*1/15);
        racket = new Rackets(SCR_WIDTH/2 - SCR_WIDTH*1/10/2, SCR_HEIGHT*1/8,
                SCR_WIDTH*1/10+100, SCR_HEIGHT/50);
        bot = new Bot(SCR_WIDTH/2 - SCR_WIDTH*1/10/2, SCR_HEIGHT-SCR_HEIGHT*1/8,
                SCR_WIDTH*1/10, SCR_HEIGHT/50, 0);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        if(Gdx.input.isTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            if (touch.y < SCR_HEIGHT*49/100) {
                racket.move(touch.x, touch.y);
            } else {
                racket.move(touch.x, SCR_HEIGHT*49/100);
            }
        }
        bot.move(ball.getX(), ball.getY());
        bot.speed(ball.getSpeedX(), ball.getSpeedY(), ball.getStartRadius());
        ball.move();
        if (ball.punch(racket.getX(), racket.getY(), racket.getWidth(), racket.getHeight(),
                racket.getSpeedX(), racket.getSpeedY(), 0)) {
            if (pingPong.isSoundOn) {
                if (Math.abs(racket.getSpeedX()) + Math.abs(racket.getSpeedY()) > 0.8f) {
                    sndPunchOnRacket.play(1f);
                } else {
                    sndPunchOnRacket.play(0.5f);
                }
            }
        }
        if (ball.punch(bot.getX(), bot.getY(), bot.getWidth(), bot.getHeight(), bot.getSpeedX(),
                bot.getSpeedY(), 1)) {
            if (pingPong.isSoundOn) {
                if (Math.abs(bot.getSpeedX()) + Math.abs(bot.getSpeedY()) > 0.8f) {
                    sndPunchOnRacket.play(1f);
                } else {
                    sndPunchOnRacket.play(0.5f);
                }

            }
            bot.punch();
        }
        if (ball.punchOnTable()) {
            if (pingPong.isSoundOn & ball.ballOnTable()) {
                sndPunchOnTable.play(1);
            }
        }
        ScreenUtils.clear(1, 0, 0, 1);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        batch.draw(imgTable, TABLE_X, TABLE_Y, WIDTH_TABLE, HEIGHT_TABLE);
        batch.draw(imgBall, ball.getX(), ball.getY(), ball.getRadius(), ball.getRadius());
        batch.draw(imgBlock, racket.getX(), racket.getY(), racket.getWidth(), racket.getHeight());
        batch.draw(imgBlock, bot.getX(), bot.getY(), bot.getWidth(), bot.getHeight());
        fontLarge.draw(batch, "Bot", SCR_WIDTH/2 + SCR_WIDTH*1/10 -
                new GlyphLayout(fontLarge, "Bot").width, SCR_HEIGHT*98/100);
        fontLarge.draw(batch, "You", SCR_WIDTH/2 - SCR_WIDTH*1/10, SCR_HEIGHT*98/100);
        fontLarge.draw(batch, Integer.toString(scoreBot), SCR_WIDTH/2 + SCR_WIDTH*1/10 -
                new GlyphLayout(fontLarge, Integer.toString(scoreBot)).width, SCR_HEIGHT*95/100);
        fontLarge.draw(batch, Integer.toString(scorePlayer), SCR_WIDTH/2 - SCR_WIDTH*1/10, SCR_HEIGHT*95/100);
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
        imgTable.dispose();
        imgBall.dispose();
        imgBlock.dispose();
        imgBackGround.dispose();
        sndPunchOnRacket.dispose();
        sndPunchOnTable.dispose();
        fontLarge.dispose();
    }


}
