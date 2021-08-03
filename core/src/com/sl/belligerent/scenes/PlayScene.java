package com.sl.belligerent.scenes;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sl.belligerent.GameCore;
import com.sl.belligerent.core.textures.CommonTexture;

public class PlayScene extends Scene {

	private Texture texture;
	private Texture pou;
	private Vector2 pouCords;
	public Map<String, TextureRegion> texRegs;
	
	public PlayScene(SceneManager manager) {
		super(manager);

		texture = new Texture(Gdx.files.internal("Textures/Sprites/ground_atlas.png"));
		texRegs = new HashMap<String, TextureRegion>();
		
		TextureRegion tmp[][] = TextureRegion.split(texture, texture.getWidth() / 2, texture.getHeight());
		texRegs.put("bricks", tmp[0][0]);
		texRegs.put("bricks_bad", tmp[0][1]);
		
		pou = new Texture(Gdx.files.internal("Textures/Sprites/pou.png"));
		pouCords = new Vector2(0, 0);
	}

	@Override
	protected void handleInput() {
		// TODO Auto-generated method stub
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			manager.set(new MenuScene(manager));
		}
		if(Gdx.input.isButtonPressed(Buttons.LEFT)) {
			pouCords.x = Gdx.input.getX() - 32;
			pouCords.y = GameCore.HEIGHT - Gdx.input.getY() - 32;
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
				TextureRegion tex;
				Random r = new Random();
				double l = r.nextDouble();
				if(l > 0.3) tex = texRegs.get("bricks");
				else tex = texRegs.get("bricks_bad");
				sb.draw(tex, i*GameCore.DEF_SIZE, j*GameCore.DEF_SIZE);
			}
		}
		
		sb.draw(pou, pouCords.x, pouCords.y);

		sb.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
