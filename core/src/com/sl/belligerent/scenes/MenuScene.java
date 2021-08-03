package com.sl.belligerent.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
		}
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		handleInput();
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		
		sb.draw(background.getTexture(), 0, 0);
		
		sb.end();
	}

	@Override
	public void dispose() {
		background.dispose();
	}

	
}
