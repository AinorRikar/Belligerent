package com.sl.belligerent.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sl.belligerent.GameCore;
import com.sl.belligerent.core.textures.CommonTexture;
import com.sl.belligerent.core.units.CommonUnit;
import com.sl.belligerent.core.units.MovableUnit;
import com.sl.belligerent.core.world.MapManager;

public class PlayScene extends Scene {

	MovableUnit unit;
	CommonUnit selected;
	
	String s;
	
	public PlayScene(GameCore game) {
		super(game);
		// TODO Auto-generated constructor stub
		MapManager.createLevel("Maps/BasicVillage.tmx");
		
		s = "Null";
		
		unit = new MovableUnit(new CommonTexture("Textures/Sprites/Units/pou.png", 64, 64), 2f);
		stage.addActor(unit);
		
		stage.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				Actor a = stage.hit(x, y, !isScenePaused);
				if(a instanceof CommonUnit) {
					System.out.println("OK");
					selected = (CommonUnit) a;
					System.out.println(selected.getPos());
					System.out.println(((Actor)selected).getX() + ":" + ((Actor)selected).getY());
					s = selected.toString() + " Position: " + selected.getPos();
				}
				else
				{
					s = "Null";
				}
			}
		});
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		if(Gdx.input.isButtonPressed(Buttons.MIDDLE)) {
			float x = -Gdx.input.getDeltaX();
			float y = Gdx.input.getDeltaY();
			camera.translate(new Vector2(x, y));
		}
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		stage.act(delta);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        handleInput();
        
        camera.update();
        //game.batch.setProjectionMatrix(camera.getCamera().combined);
        
        update(delta);

        batch.begin();
        
        MapManager.render(batch, camera.getCamera());
        game.fontSmall.draw(batch, s, 50, 50);
        
        batch.end();
        
        stage.draw();
	}
	
	

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		stage.getViewport().update(width, height, false);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
	}
}
