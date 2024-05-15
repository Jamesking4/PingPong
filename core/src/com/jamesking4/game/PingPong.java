package com.jamesking4.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class PingPong extends Game {
	public static final float SCR_WIDTH = 1080, SCR_HEIGHT = 2400;

	SpriteBatch batch;
	OrthographicCamera camera;
	Vector3 touch;

	ScreenMenu screenMenu;
	ScreenGame screenGame;
	ScreenSettings screenSettings;
	//ScreenAbout screenAbout;

	boolean isSoundOn = true;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
		touch = new Vector3();

		screenMenu = new ScreenMenu(this);
		screenGame = new ScreenGame(this);
		screenSettings = new ScreenSettings(this);
		//screenAbout = new ScreenAbout(this);

		setScreen(screenMenu);
	}

	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
