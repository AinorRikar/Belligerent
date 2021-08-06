package com.sl.belligerent.core.units;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sl.belligerent.core.textures.CommonTexture;
import com.sl.belligerent.core.textures.MultiTexture;
import com.sl.belligerent.core.world.MapManager;

public class MovableUnit extends CommonUnit{
	/*
	 * STATES
	 * 0 - no action
	 * 
	 * 1 - move up
	 * 2 - move down
	 * 3 - move left
	 * 4 - move right
	 */
	protected float speed;
	protected Vector2 dest, dir;
	
	protected float moral;
	protected float hunger;
	protected float stamina;
	
	protected float str;
	protected float ag;
	protected float in;
	
	public MovableUnit(MultiTexture texture, float speed) {
		super(texture);
		dest = new Vector2(0, 0);
		dir = new Vector2(0, 0);
		this.speed = speed;
		spawn(0, 0, (int) MapManager.getCurrentMapSize().x, (int) MapManager.getCurrentMapSize().y);
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
		} while(!MapManager.isCellMoveable((int) pos.x, (int) pos.y));
		setPosition(x * 32, y * 32);
		setSize(64, 64);
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		if(state == 0) {
			Random random = new Random();
			if(random.nextFloat() < 0.99) {
				state = 0;
			}
			else
			{
				randomMove(dt);
			}
		}
		else if(state >= 1 && state <= 4) {
			move(dt, dest);
		}
	}
	
	protected void randomMove(float dt) {
		Random random = new Random();
		state = random.nextInt(4) + 1;
		switch (state) {
		case 1:
			dest.y = 1;
			dir.y = 1;
			break;
		case 2:
			dest.y = -1;
			dir.y = -1;
			break;
		case 3:
			dest.x = -1;
			dir.x = -1;
			break;
		case 4:
			dest.x = 1;
			dir.x = 1;
			break;
		}
		
		dest.x += pos.x;
		dest.y += pos.y;
		
		move(dt, dest);
	}
	
	protected void move(float dt, Vector2 dest) {
		boolean movementEnd = false;
		if(!MapManager.isCellMoveable((int) dest.x, (int) dest.y)) {
			movementEnd = true;
			state = 0;
			dir.x = 0;
			dir.y = 0;
			this.dest.x = 0;
			this.dest.y = 0;
			return;
		}
		if(state == 1 && pos.y > dest.y) {
			pos = dest.cpy();
			movementEnd = true;
		}
		else if(state == 2 && pos.y < dest.y) {
			pos = dest.cpy();
			movementEnd = true;
		}
		else if(state == 3 && pos.x < dest.x) {
			pos = dest.cpy();
			movementEnd = true;
		}
		else if(state == 4 && pos.x > dest.x) {
			pos = dest.cpy();
			movementEnd = true;
		}
		
		if(!movementEnd) {
			pos.add(dir.cpy().scl(speed * dt));
		}
		else {
			state = 0;
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
		switch (state) {
		case 1:
			tr = texture.getTextureRegion(64, 64, 64, 64);
			break;
		case 2:
			tr = texture.getTextureRegion(0, 0, 64, 64);
			break;
		case 3:
			tr = texture.getTextureRegion(64, 0, 64, 64);
			break;
		case 4:
			tr = texture.getTextureRegion(0, 64, 64, 64);
			break;
		}
		return tr;
	}
}
