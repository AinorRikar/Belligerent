package com.sl.belligerent.core.world;

import java.util.Vector;

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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sl.belligerent.core.units.StaticUnit;

public class Map extends Actor { 
	protected TiledMap map;
	protected OrthogonalTiledMapRenderer renderer;
	protected OrthographicCamera cam;
	
	public Vector<Actor> unitsList;
	
	public Map() {
		super();
		unitsList = new Vector<Actor>();
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
	
	public void addUnit(Actor actor) {
		unitsList.add(actor);
	}
	
	public void removeUnit(Actor actor) {
		unitsList.remove(actor);
	}
	
	public boolean isUnitInPos(int X, int Y, int w, int h, Actor testingActor) {
		for(int i = 0; i < unitsList.size(); i++) {
			Actor actor = unitsList.get(i);
			
			float posX = actor.getX();
			float posY = actor.getY();
			float posX2 = actor.getWidth() + posX;
			float posY2 = actor.getHeight() + posY;
			
			int x = X * 32;
			int y = Y * 32;
			
			if(x >= posX - w && x <= posX2 - 1 && y >= posY - h && y <= posY2 - 1 && actor != testingActor) {
				return true;
			}
		}
		return false;
	}
}
