package com.sl.belligerent.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class PlayScene extends Scene {

	public PlayScene(SceneManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void handleInput() {
		// TODO Auto-generated method stub
		if(Gdx.input.justTouched()) {
			manager.set(new MenuScene(manager));
		}
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		handleInput();
	}

	@Override
	public void render(SpriteBatch sb) {
		ScreenUtils.clear(1, 0, 0, 1);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
