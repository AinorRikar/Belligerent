package com.sl.belligerent;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sl.belligerent.scenes.MenuScene;
import com.sl.belligerent.scenes.SceneManager;

public class GameCore extends Game {
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	public static final int DEF_SIZE = 64;
	
	public static final String TITLE = "Belligerent";
	
	public SpriteBatch batch;
	public BitmapFont fontMain;
	public BitmapFont fontSmall;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		
		fontMain = new BitmapFont();
		fontMain.getData().setScale(5);
		
		fontSmall = new BitmapFont();
		
		this.setScreen(new MenuScene(this));
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		this.getScreen().dispose();
	}
}
