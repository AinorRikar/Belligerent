package com.sl.belligerent.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.sl.belligerent.GameCore;
import com.sl.belligerent.core.camera.CommonCamera;

public abstract class Scene implements Screen {
	final GameCore game;
	
	protected CommonCamera camera;
	protected Vector3 mouse;
	protected Rectangle glViewport;
	protected SpriteBatch batch;
	
	protected Stage stage;
	
	protected boolean isScenePaused = false;
	
	public Scene(final GameCore game)
	{
		this.game = game;
		camera = new CommonCamera(new OrthographicCamera(GameCore.WIDTH, GameCore.HEIGHT));
		camera.setPos(GameCore.WIDTH / 2, GameCore.HEIGHT / 2, 0);
		camera.getCamera().setToOrtho(false, GameCore.WIDTH, GameCore.HEIGHT);
		mouse = new Vector3();
		this.batch = game.batch;
		stage = new Stage(new FillViewport(GameCore.WIDTH, GameCore.HEIGHT, this.camera.getCamera()), batch);
		Gdx.input.setInputProcessor(stage);
	}
	
	public abstract void handleInput();
	public abstract void update(float delta);
}
