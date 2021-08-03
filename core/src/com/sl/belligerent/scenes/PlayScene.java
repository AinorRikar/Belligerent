package com.sl.belligerent.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sl.belligerent.GameCore;
import com.sl.belligerent.core.textures.CommonTexture;

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
		
		if(isScenePaused) sb.setColor(0.5f, 0.5f, 0.5f, 1.0f);
		else sb.setColor(1f, 1f, 1f, 1f);
		
		sb.begin();
		for(int i = 0; i * GameCore.DEF_SIZE < GameCore.WIDTH; i++)
		{
			for(int j = 0; j * GameCore.DEF_SIZE < GameCore.HEIGHT; j++)
			{
				CommonTexture tex = new CommonTexture("Textures/Sprites/stone_brick.png", GameCore.DEF_SIZE, GameCore.DEF_SIZE);
				sb.draw(tex.getTexture(), i * GameCore.DEF_SIZE, j * GameCore.DEF_SIZE);
			}
		}

		sb.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
