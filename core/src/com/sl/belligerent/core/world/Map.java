package com.sl.belligerent.core.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapImageLayer;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Map extends Actor { 
	protected TiledMap map;
	protected OrthogonalTiledMapRenderer renderer;
	protected OrthographicCamera cam;
	
	public Map() {
		super();
	}
	
	public void createLevel(String path, OrthographicCamera cam)
	{
		map = new TmxMapLoader().load(path);
		renderer = new OrthogonalTiledMapRenderer(map, 1/1f);
		this.cam = cam;
	}
	
	public void update() {    
		
	}
	
	@Override
	public void draw(Batch sb, float parentAlpha)
	{
		renderer.setView(cam);
		renderer.render();
	}
	
	public TiledMap getMap() {
		return map;
	}
}
