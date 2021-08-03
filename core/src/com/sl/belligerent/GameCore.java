package com.sl.belligerent;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sl.belligerent.scenes.MenuScene;
import com.sl.belligerent.scenes.SceneManager;

public class GameCore extends ApplicationAdapter {
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	
	public static final String TITLE = "Belligerent";
	
	private SceneManager sceneManager;
	
	private SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		sceneManager = new SceneManager();
		
		MenuScene mainMenu = new MenuScene(sceneManager);
		sceneManager.push(mainMenu);
	}

	@Override
	public void render () {
		sceneManager.update(Gdx.graphics.getDeltaTime());
		sceneManager.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
