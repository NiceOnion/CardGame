package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MyGdxGame extends Game {

	public static MyGdxGame INSTANCE;
	private int screenWidth, screenHeight;
	private OrthographicCamera camera;

	public MyGdxGame(){
		INSTANCE = this;
	}

	@Override
	public void create() {
		this.screenHeight = Gdx.graphics.getHeight();
		this.screenWidth = Gdx.graphics.getWidth();
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false,screenWidth,screenHeight);

		setScreen(new GameScreen(camera));
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}
}
