package com.sl.belligerent.core.units;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sl.belligerent.core.textures.CommonTexture;
import com.sl.belligerent.core.textures.MultiTexture;

public abstract class CommonUnit extends Actor{
	protected Vector2 pos;
	protected MultiTexture texture;
	public int state;
	
	protected float health;
	
	protected String UnitName;
	
	public CommonUnit(MultiTexture texture, String name) {
		super();
		this.texture = texture;
		state = 0;
		UnitName = name;
	}
	
	public Vector2 getMapPos() {
		return new Vector2(pos.x * 32, pos.y * 32);
	}
	
	public Vector2 getPos() {
		return new Vector2(pos.x, pos.y);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		update(delta);
	}
	
	public String getName()
	{
		return UnitName;
	}
	
	public abstract void spawn(int minX, int minY, int maxX, int maxY);
	
	public abstract void update(float dt);
	
	public abstract void draw(Batch sb, float parentAlpha);
}
