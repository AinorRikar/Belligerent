package com.sl.belligerent.core.units;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.sl.belligerent.core.textures.MultiTexture;
import com.sl.belligerent.core.world.MapManager;

public class StaticUnit extends CommonUnit{
	
	private boolean isMain;

	public StaticUnit(MultiTexture texture, String name, boolean isMain) {
		super(texture, name);
		// TODO Auto-generated constructor stub
		this.isMain = isMain;
		setSize(192, 192);
		spawn(0, 0, (int) MapManager.getCurrentMapSize().x, (int) MapManager.getCurrentMapSize().y);
	}

	@Override
	public void spawn(int minX, int minY, int maxX, int maxY) {
		// TODO Auto-generated method stub
		int x, y;

		x = (minX + maxX) / 2;
		y = (minY + maxY) / 2;
		
		pos = new Vector2(x, y);
		
		setPosition(x * 32, y * 32);
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Batch sb, float parentAlpha) {
		// TODO Auto-generated method stub
		sb.setColor(1, 1, 1, parentAlpha);
		sb.draw(texture.getTexture(), getX(), getY());
	}
	
	public boolean isMain() {
		return isMain;
	}
}
