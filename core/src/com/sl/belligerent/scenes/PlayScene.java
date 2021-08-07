package com.sl.belligerent.scenes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sl.belligerent.GameCore;
import com.sl.belligerent.core.hordes.CommonHorde;
import com.sl.belligerent.core.textures.CommonTexture;
import com.sl.belligerent.core.textures.MultiTexture;
import com.sl.belligerent.core.units.CommonUnit;
import com.sl.belligerent.core.units.MovableUnit;
import com.sl.belligerent.core.world.Map;
import com.sl.belligerent.core.world.MapManager;

public class PlayScene extends Scene {

	CommonUnit selected;
	Map map;
	CommonTexture sel;
	
	String s;
	
	Label l, l2, l3, l4;
	Image img;
	
	CommonHorde horde;
	
	Music m;
	
	public PlayScene(final GameCore game) {
		super(game);
		// TODO Auto-generated constructor stub
		map = new Map();
		map.createLevel("Maps/BasicVillage.tmx", camera.getCamera());
		MapManager.setMap(map);
		
		horde = new CommonHorde();
		
		horde.createHorde(15);
		
		s = "";
		
		stage.addActor(map);
		stage.addActor(horde.getGroup());
		
		img = new Image(new Texture(Gdx.files.internal("Textures/Sprites/Units/pou.png")));
		img.setPosition(0, 0);
		gui.addActor(img);
		
		l = new Label(s, new LabelStyle(game.fontSmall, Color.WHITE));
		l.setPosition(100, 30);
		l2 = new Label(s, new LabelStyle(game.fontSmall, Color.WHITE));
		l2.setPosition(280, 40);
		l3 = new Label(s, new LabelStyle(game.fontSmall, Color.WHITE));
		l3.setPosition(400, 40);
		l4 = new Label(s, new LabelStyle(game.fontSmall, Color.WHITE));
		l4.setPosition(10, GameCore.HEIGHT - 40);
		gui.addActor(l);
		gui.addActor(l2);
		gui.addActor(l3);
		gui.addActor(l4);
		
		stage.addActor(gui);
		
		camera.setPos(MapManager.getCurrentMapSize().x * 32 / 2, MapManager.getCurrentMapSize().y * 32 / 2, 0);
		
		stage.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				// TODO Auto-generated method stub
				if(keycode == Keys.FORWARD_DEL) {
					if(selected != null && selected instanceof MovableUnit) {
						Actor a = (Actor) selected;
						horde.removeActor(a);
						selected = null;
					}
				}
				if(keycode == Keys.ENTER) {
					MovableUnit unit = new MovableUnit(new MultiTexture("Textures/Sprites/Units/Mummai.png", 128, 128), "Blue Mummai", horde, 2f);
					horde.addActor(unit);
				}
				return super.keyDown(event, keycode);
			}
		});
		
		stage.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				Actor a = stage.hit(x, y, !isScenePaused);
				if(a instanceof CommonUnit) {
					System.out.println("OK");
					selected = (CommonUnit) a;
					sel = new CommonTexture("Textures/Sprites/selected.png", (int) a.getWidth(), (int) a.getHeight());
					System.out.println(selected.getPos());
					System.out.println(((Actor)selected).getX() + ":" + ((Actor)selected).getY());
				}
				else
				{
					selected = null;
					s = "";
					l.setText(s);
					l2.setText(s);
					l3.setText(s);
				}
			}
		});
		
		img.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				m.stop();
				game.setScreen(new MenuScene(game));
			}
		});
		
		m = Gdx.audio.newMusic(Gdx.files.internal("Sounds/Music/game.mp3"));
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
		MapManager.update(delta);
		stage.act(delta);
		
		l4.setText("Stones: " + horde.getStones() + "\nLogs: " + horde.getLogs() + "\nMeat: " + horde.getMeat() + "\nHerbs: " + horde.getHerbs());
		
		if(selected instanceof CommonUnit) {
			s = ((CommonUnit)selected).getName() + "\nPosition: " + selected.getPos() + "\nHealth: " + ((CommonUnit)selected).getHP();
			l.setText(s);
		}
		else
		{
			l.setText("");
		}
		if(selected instanceof MovableUnit) {
			s = "\nStamina: " + ((MovableUnit)selected).getStamina() + "\nHunger: " + ((MovableUnit)selected).getHunger() + "\nMoral: " + ((MovableUnit)selected).getMoral();
			l2.setText(s);
			s = "\nStr: " + ((MovableUnit)selected).getStr() + "\nAg: " + ((MovableUnit)selected).getAg() + "\nIn: " + ((MovableUnit)selected).getIn();
			l3.setText(s);
		}
		else
		{
			l2.setText("");
			l3.setText("");
		}
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        handleInput();
        
        update(delta);
        
        camera.update();
        game.batch.setProjectionMatrix(camera.getCamera().combined);
        
        stage.draw();

        batch.begin();
        
        if(selected != null) {
        	batch.draw(sel.getTexture(), selected.getPos().x * 32, selected.getPos().y * 32);
        }
        
        batch.end();
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
