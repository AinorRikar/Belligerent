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
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sl.belligerent.GameCore;
import com.sl.belligerent.core.textures.CommonTexture;
import com.sl.belligerent.core.units.CommonUnit;
import com.sl.belligerent.core.units.MovableUnit;
import com.sl.belligerent.core.world.MapManager;

public class PlayScene extends Scene {

	private CommonUnit unit;
	private boolean selected;
	
	public PlayScene(SceneManager manager) {
		super(manager);
		
		MapManager.createLevel("Maps/BasicVillage.tmx");
		
		selected = false;
		unit = new MovableUnit(new CommonTexture("Textures/Sprites/Units/pou.png", 64, 64), 1f);
		do {
			unit.spawn(0, 0, ((TiledMapTileLayer)MapManager.getMap().getLayers().get(0)).getWidth(), ((TiledMapTileLayer)MapManager.getMap().getLayers().get(0)).getHeight(), (TiledMapTileLayer) MapManager.getMap().getLayers().get("Ground"));
			
			Vector2 unitPos = unit.getMapPos();
			camera.setPos(unitPos.x, unitPos.y, 0);
		} while (((TiledMapTileLayer)MapManager.getMap().getLayers().get("Ground")).getCell((int)unit.getPos().x, (int)unit.getPos().y) == null);
	}

	@Override
	protected void handleInput() {
		// TODO Auto-generated method stub
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			manager.set(new MenuScene(manager));
		}
		if(Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
			float x = Gdx.input.getX();
			float y = Gdx.input.getY();
			
			Vector2 worldClick = camera.getWorldPosFromCamera(x, GameCore.HEIGHT - y);
			
			if(worldClick.x >= unit.getMapPos().x && worldClick.y >= unit.getMapPos().y && worldClick.x <= unit.getMapPos().x + 64 && worldClick.y <= unit.getMapPos().y + 64) selected = true;
			else selected = false;
			
			System.out.println("Selected: " + selected + " Pos: " + unit.getMapPos()	+ "Click Pos: " + Gdx.input.getX() + ":" + Gdx.input.getY());
			System.out.println("Cam Translation: " + camera.getCamera().position);
			System.out.println("World Click: " + worldClick);
		}
		if(Gdx.input.isButtonPressed(Buttons.MIDDLE)) {
			float dX = Gdx.input.getDeltaX();
			float dY = Gdx.input.getDeltaY();
			
			camera.translate(new Vector2(-dX, dY));
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
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			camera.translate(new Vector2(-5, 0));
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			camera.translate(new Vector2(5, 0));
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			camera.translate(new Vector2(0, -5));
		}
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			camera.translate(new Vector2(0, 5));
		}
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		handleInput();
		unit.update(dt);
	}

	@Override
	public void render(SpriteBatch sb) {

		camera.update();
		sb.setProjectionMatrix(camera.getCamera().combined);
		
		if(isScenePaused) sb.setColor(0.5f, 0.5f, 0.5f, 3.0f);
		else sb.setColor(1f, 1f, 1f, 1f);
		
		sb.begin();
		
		MapManager.render(sb, camera.getCamera());
		
		unit.render(sb);

		sb.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
