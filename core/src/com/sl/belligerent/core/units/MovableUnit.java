package com.sl.belligerent.core.units;

import java.awt.Dimension;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sl.belligerent.core.hordes.CommonHorde;
import com.sl.belligerent.core.textures.CommonTexture;
import com.sl.belligerent.core.textures.MultiTexture;
import com.sl.belligerent.core.world.MapManager;

public class MovableUnit extends CommonUnit{
	/*
	 * STATES
	 * 0 - no action up
	 * 1 - no action down
	 * 2 - no action left
	 * 3 - no action right
	 * 
	 * 4 - move up
	 * 5 - move down
	 * 6 - move left
	 * 7 - move right
	 * 
	 * 8 - sleep
	 */
	protected int countOfStates = 9;
	
	protected float speed;
	protected Vector2 dest, dir;
	
	protected float moral;
	protected float hunger;
	protected float stamina;
	protected float moralMax;
	protected float hungerMax;
	protected float staminaMax;
	
	protected float str;
	protected float ag;
	protected float in;
	
	public MovableUnit(MultiTexture texture, String name, CommonHorde horde, float speed) {
		super(texture, name, horde);
		dest = new Vector2(0, 0);
		dir = new Vector2(0, 0);
		this.speed = speed;
		setSize(64, 64);
		spawn(0, 0, (int) MapManager.getCurrentMapSize().x, (int) MapManager.getCurrentMapSize().y);
		health = 100;
		healthMax = health;
		moral = 100;
		moralMax = moral;
		hunger = 100;
		hungerMax = hunger;
		stamina = 100;
		staminaMax = stamina;
		
		Random r = new Random();
		str = 1 + r.nextInt(10);
		ag = 1 + r.nextInt(10);
		in = 1 + r.nextInt(10);
	}

	public float getMoral() {
		return moral;
	}

	public float getHunger() {
		return hunger;
	}

	public float getStamina() {
		return stamina;
	}

	public float getStr() {
		return str;
	}

	public float getAg() {
		return ag;
	}

	public float getIn() {
		return in;
	}

	@Override
	public void spawn(int minX, int minY, int maxX, int maxY) {
		// TODO Auto-generated method stub
		Random random = new Random();
		int x, y;
		do {
			x = minX + random.nextInt(maxX - minX);
			y = minY + random.nextInt(maxY - minY);
			pos = new Vector2(x, y);
		} while(!MapManager.isCellMoveableFor((int) pos.x, (int) pos.y, (int) getWidth(), (int) getHeight(), this));
		setPosition(x * 32, y * 32);
	}
	
	public void heal(float dt) {
		if(health < healthMax) {
			healOnce((dt / 5) * (str + 0.5f * in));
		}
	}
	
	public void healOnce(float hp) {
		health += hp;
		if(health > healthMax) {
			health = healthMax;
		}
	}

	@Override
	public void update(float dt) {
		float[] needs = new float[countOfStates];
		for(int i = 0; i < countOfStates; i++) {
			needs[i] = 0;
			if(i < 4) {
				needs[i] += moralMax - moral + 5;
			}
		}
		
		if(health < (600 / (str + 7)) || state == 8 && health != healthMax)
		{
			needs[8] += 40 + (healthMax - health) / (str / 3);
		}
		
		// STATEMACHINE
		int index = 0;
		float max = 0;
		for(int i = 0; i < countOfStates; i++) {
			if(needs[i] > max) {
				max = needs[i];
				index = i;
			}
		}
		if(index < 4 && state < 8) {
			
		}
		else {
			state = index;
		}
		
		if(state >= 0 && state <= 3) {
			Random random = new Random();
			if(random.nextFloat() < 0.99) {
				
			}
			else
			{
				randomMove(dt);
			}
		}
		else if(state >= 4 && state <= 7) {
			move(dt, dest, false);
		}
		else if(state == 8) {
			heal(dt);
		}
	}
	
	protected void randomMove(float dt) {
		Random random = new Random();
		state = random.nextInt(4) + 4;
		switch (state) {
		case 4:
			dest.y = 1;
			dir.y = 1;
			break;
		case 5:
			dest.y = -1;
			dir.y = -1;
			break;
		case 6:
			dest.x = -1;
			dir.x = -1;
			break;
		case 7:
			dest.x = 1;
			dir.x = 1;
			break;
		}
		
		dest.x += pos.x;
		dest.y += pos.y;
		
		move(dt, dest, true);
	}
	
	protected void move(float dt, Vector2 dest, boolean start) {
		boolean movementEnd = false;
		
		if(start && !MapManager.isCellMoveableFor((int) dest.x, (int) dest.y, (int) getWidth(), (int) getHeight(), this)) {
			movementEnd = true;
			state -= 4;
			dir.x = 0;
			dir.y = 0;
			this.dest.x = 0;
			this.dest.y = 0;
			return;
		}
		if(state == 4 && pos.y > dest.y) {
			pos = dest.cpy();
			movementEnd = true;
		}
		else if(state == 5 && pos.y < dest.y) {
			pos = dest.cpy();
			movementEnd = true;
		}
		else if(state == 6 && pos.x < dest.x) {
			pos = dest.cpy();
			movementEnd = true;
		}
		else if(state == 7 && pos.x > dest.x) {
			pos = dest.cpy();
			movementEnd = true;
		}
		
		if(!movementEnd) {
			pos.add(dir.cpy().scl(speed * dt));
		}
		else {
			state -= 4;
			dir.x = 0;
			dir.y = 0;
			this.dest.x = 0;
			this.dest.y = 0;
		}
		
		setPosition(pos.x * 32, pos.y * 32);
	}
	
	@Override
	public void draw(Batch sb, float parentAlpha) {
		sb.setColor(1, 1, 1, parentAlpha);
		sb.draw(getCurReg(), getX(), getY());
	}
	
	public TextureRegion getCurReg() {
		TextureRegion tr = texture.getTextureRegion(0, 0, 64, 64);
		switch (state % 4) {
		case 0:
			tr = texture.getTextureRegion(64, 64, 64, 64);
			break;
		case 1:
			tr = texture.getTextureRegion(0, 0, 64, 64);
			break;
		case 2:
			tr = texture.getTextureRegion(64, 0, 64, 64);
			break;
		case 3:
			tr = texture.getTextureRegion(0, 64, 64, 64);
			break;
		}
		return tr;
	}
	
	public void moveTo(Vector2 pos) {
		
	}
}
