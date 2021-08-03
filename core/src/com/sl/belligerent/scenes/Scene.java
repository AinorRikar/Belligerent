package com.sl.belligerent.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sl.belligerent.GameCore;
import com.sl.belligerent.core.camera.CommonCamera;

public abstract class Scene {
	protected CommonCamera camera;
	protected Vector3 mouse;
	protected SceneManager manager;
	protected Rectangle glViewport;
	
	protected boolean isScenePaused = false;
	
	public Scene(SceneManager manager)
	{
		this.manager = manager;
		camera = new CommonCamera(new OrthographicCamera(GameCore.WIDTH, GameCore.HEIGHT));
		camera.setPos(GameCore.WIDTH / 2, GameCore.HEIGHT / 2, 0);
		mouse = new Vector3();
	}
	
	protected abstract void handleInput();
	public abstract void update(float dt);
	public abstract void render(SpriteBatch sb);
	public abstract void dispose();
}
