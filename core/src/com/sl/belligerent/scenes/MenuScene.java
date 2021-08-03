package com.sl.belligerent.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.loaders.ShaderProgramLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sl.belligerent.GameCore;
import com.sl.belligerent.core.textures.CommonTexture;

public class MenuScene extends Scene {

	private CommonTexture background;
	
	public MenuScene(SceneManager manager) {
		super(manager);
		
		background = new CommonTexture("Textures/Backgrounds/bg.png", GameCore.WIDTH, GameCore.HEIGHT);
	}

	@Override
	protected void handleInput() {
		if(!isScenePaused) {
			if(Gdx.input.justTouched()) {
				manager.set(new PlayScene(manager));
			}
			if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
				Gdx.app.exit();
			}
			if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
				isScenePaused = true;
			}
		}
		else
		{
			if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
				isScenePaused = false;
			}
		}
			
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		handleInput();
	}

	@Override
	public void render(SpriteBatch sb) {
		
		camera.update();
		sb.setProjectionMatrix(camera.getCamera().combined);
		
		if(isScenePaused) sb.setColor(0.5f, 0.5f, 0.5f, 1.0f);
		else sb.setColor(1f, 1f, 1f, 1f);
		
		sb.begin();
		
		Texture bgTexture = background.getTexture();
		sb.draw(bgTexture, 0, 0);
		
		sb.end();
	}

	@Override
	public void dispose() {
		background.dispose();
	}

	
}
