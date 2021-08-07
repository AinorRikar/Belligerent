package com.sl.belligerent.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sl.belligerent.GameCore;

public class MenuScene extends Scene {

	ImageButton start, exit, sett;
	Music m;
	Sound click;
	
	public MenuScene(final GameCore game) {
		super(game);
		// TODO Auto-generated constructor stub
		Skin skin = new Skin();
		TextureAtlas btnAtlas = new TextureAtlas(Gdx.files.internal("Textures/Atlas/buttons_menu.atlas"));
		skin.addRegions(btnAtlas);
		
		ImageButtonStyle styleStart = new ImageButtonStyle();
		styleStart.up = skin.getDrawable("start_button");
		styleStart.down = skin.getDrawable("start_button_pressed");
		styleStart.checked = skin.getDrawable("start_button_pressed");
		start = new ImageButton(styleStart);
		
		ImageButtonStyle styleSet = new ImageButtonStyle();
		styleSet.up = skin.getDrawable("settings_button");
		styleSet.down = skin.getDrawable("settings_button_pressed");
		styleSet.checked = skin.getDrawable("settings_button");
		sett = new ImageButton(styleSet);
		
		ImageButtonStyle styleExit = new ImageButtonStyle();
		styleExit.up = skin.getDrawable("exit_button");
		styleExit.down = skin.getDrawable("exit_button_pressed");
		styleExit.checked = skin.getDrawable("exit_button_pressed");
		exit = new ImageButton(styleExit);
		
		start.setPosition(50, 500);
		sett.setPosition(50, 300);
		exit.setPosition(50, 100);
		stage.addActor(start);
		stage.addActor(sett);
		stage.addActor(exit);
		
		click = Gdx.audio.newSound(Gdx.files.internal("Sounds/Effects/DiClick.mp3"));
		
		start.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				click.play();
				m.stop();
				game.setScreen(new PlayScene(game));
			}
		});
		
		sett.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				click.play();
			}
		});
		
		exit.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				click.play();
				Gdx.app.exit();
			}
		});
		
		m = Gdx.audio.newMusic(Gdx.files.internal("Sounds/Music/menu.mp3"));
		m.setVolume(0.5f);
		m.setLooping(true);
		m.play();
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
        
        game.fontMain.draw(batch, "BELLIGERENT", 100, GameCore.HEIGHT - 100);
        game.fontSmall.draw(batch, "by SL team", 100, GameCore.HEIGHT - 200);
        
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
