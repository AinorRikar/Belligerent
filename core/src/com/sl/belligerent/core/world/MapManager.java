package com.sl.belligerent.core.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
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

public class MapManager { 
	static TiledMap map;
	static OrthogonalTiledMapRenderer renderer;
	
	public static void createLevel(String path)
	{
		map = new TmxMapLoader().load(path);
		renderer = new OrthogonalTiledMapRenderer(map, 1/1f);
	}
	public static void update() {    
		
	}
	public static void render(SpriteBatch sb, OrthographicCamera cam)
	{
		renderer.setView(cam);
		renderer.render();
	}
	public static TiledMap getMap() {
		return map;
	}
}
