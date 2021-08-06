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
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sl.belligerent.GameCore;
import com.sl.belligerent.core.textures.CommonTexture;
import com.sl.belligerent.core.units.CommonUnit;

public class MenuScene extends Scene {

	ImageButton start, exit;
	
	public MenuScene(final GameCore game) {
		super(game);
		// TODO Auto-generated constructor stub
		Skin skin = new Skin();
		TextureAtlas btnAtlas = new TextureAtlas(Gdx.files.internal("Textures/Atlas/buttons_menu.atlas"));
		skin.addRegions(btnAtlas);
		
		ImageButtonStyle styleStart = new ImageButtonStyle();
		styleStart.up = skin.getDrawable("start_button");
		styleStart.down = skin.getDrawable("start_button");
		styleStart.checked = skin.getDrawable("start_button");
		start = new ImageButton(styleStart);
		
		ImageButtonStyle styleExit = new ImageButtonStyle();
		styleExit.up = skin.getDrawable("exit_button");
		styleExit.down = skin.getDrawable("exit_button");
		styleExit.checked = skin.getDrawable("exit_button");
		exit = new ImageButton(styleExit);
		
		start.setPosition(35, 350);
		exit.setPosition(0, 50);
		stage.addActor(start);
		stage.addActor(exit);
		
		start.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new PlayScene(game));
			}
		});
		exit.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleInput() {
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        handleInput();
        
        camera.update();
        game.batch.setProjectionMatrix(camera.getCamera().combined);
        
        stage.act(delta);
        
        stage.draw();

        batch.begin();
        
        game.fontMain.draw(batch, "BELLIGERENT", 100, game.HEIGHT - 100);
        
        batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
		
	}
}
