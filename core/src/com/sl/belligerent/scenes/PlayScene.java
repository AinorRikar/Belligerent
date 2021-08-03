package com.sl.belligerent.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sl.belligerent.GameCore;
import com.sl.belligerent.core.textures.CommonTexture;

public class PlayScene extends Scene {

	private TextureAtlas atlas;
	
	private CommonTexture pou;
	private Vector2 pouCords;
	
	public PlayScene(SceneManager manager) {
		super(manager);

		atlas = new TextureAtlas("Textures/Atlas/ground.atlas");
		
		pou = new CommonTexture("Textures/Sprites/Units/pou.png", 64, 64);
		pouCords = new Vector2(0, 0);
	}

	@Override
	protected void handleInput() {
		// TODO Auto-generated method stub
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			manager.set(new MenuScene(manager));
		}
		if(Gdx.input.isButtonPressed(Buttons.LEFT)) {
			
			float centerX = GameCore.WIDTH / 2;
			float centerY = GameCore.HEIGHT / 2;
			
			float sX = Gdx.input.getX() - centerX;
			float sY = Gdx.input.getY() - centerY;
			
			sX *= camera.getZoom();
			sY *= camera.getZoom();
			
			pouCords.x = centerX + sX - 32;
			pouCords.y = GameCore.HEIGHT - sY - centerY - 32;
		}
		if(Gdx.input.isKeyPressed(Keys.EQUALS)) {
			camera.zoom(0.01f);
		}
		if(Gdx.input.isKeyPressed(Keys.MINUS)) {
			camera.zoom(-0.01f);
		}
		if(Gdx.input.isKeyJustPressed(Keys.BACKSPACE)) {
			camera.setZoom(1f);
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
		for(int i = 0; i * GameCore.DEF_SIZE < GameCore.WIDTH; i++)
		{
			for(int j = 0; j * GameCore.DEF_SIZE < GameCore.HEIGHT; j++)
			{
				AtlasRegion a = atlas.findRegion("bricks");
				Sprite s = new Sprite(a);
				sb.draw(s, i*64, j*64);
			}
		}
		
		sb.draw(pou.getTexture(), pouCords.x, pouCords.y);

		sb.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
