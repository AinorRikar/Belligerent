package com.sl.belligerent.core.units;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.sl.belligerent.core.textures.CommonTexture;

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
	
	public MovableUnit(CommonTexture texture, float speed) {
		super(texture);
		dest = new Vector2(0, 0);
		dir = new Vector2(0, 0);
		this.speed = speed;
	}

	@Override
	public void spawn(int minX, int minY, int maxX, int maxY) {
		// TODO Auto-generated method stub
		Random random = new Random();
		int x = minX + random.nextInt(maxX - minX);
		int y = minY + random.nextInt(maxY - minY);
		pos = new Vector2(x, y);
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
		System.out.println("Pos: " + pos.x + ":" + pos.y + " Dest: " + dest.x + ":" + dest.y);
		System.out.println("State: " + state);
		System.out.println("Dir: " + dir.x + ":" + dir.y);
	}
	
	protected void randomMove(float dt) {
		Random random = new Random();
		state = random.nextInt(3) + 1;
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
	}
}
