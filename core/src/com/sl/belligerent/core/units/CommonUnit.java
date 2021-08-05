package com.sl.belligerent.core.units;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.sl.belligerent.core.textures.CommonTexture;

public abstract class CommonUnit {
	protected Vector2 pos;
	protected CommonTexture texture;
	public int state;
	
	protected float health;
	
	public CommonUnit(CommonTexture texture) {
		super();
		this.texture = texture;
		state = 0;
	}
	
	public Vector2 getMapPos() {
		return new Vector2(pos.x * 32, pos.y * 32);
	}
	
	public Vector2 getPos() {
		return new Vector2(pos.x, pos.y);
	}
	
	public void render(SpriteBatch sb) {
		sb.draw(texture.getTexture(), getMapPos().x, getMapPos().y);
	}
	
	public abstract void spawn(int minX, int minY, int maxX, int maxY, TiledMapTileLayer layer);
	
	public abstract void update(float dt);
}
