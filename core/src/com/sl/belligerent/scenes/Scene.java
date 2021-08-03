package com.sl.belligerent.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.sl.belligerent.GameCore;

public abstract class Scene {
	protected OrthographicCamera camera;
	protected Vector3 mouse;
	protected SceneManager manager;
	
	protected boolean isScenePaused = false;
	
	public Scene(SceneManager manager)
	{
		this.manager = manager;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, GameCore.WIDTH, GameCore.HEIGHT);
		mouse = new Vector3();
	}
	
	protected abstract void handleInput();
	public abstract void update(float dt);
	public abstract void render(SpriteBatch sb);
	public abstract void dispose();
}
