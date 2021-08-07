package com.sl.belligerent.core.world;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sl.belligerent.core.controllers.JobController;
import com.sl.belligerent.core.hordes.CommonHorde;

public class MapManager {
	
	private static MapManager inst = new MapManager();
	
	private static JobController jc = new JobController();
	
	private static Vector2 outPos;
	
	private static Map map;
	
	private MapManager () {
		
	}
	
	public static MapManager inst() {
		return inst;
	}
	
	public static void update(float dt) {
		
	}
	
	public static void setMap(Map map) {
		inst.map = map;
	}
	
	public static void setOutPos(Vector2 pos) {
		inst.outPos = pos;
	}
	
	public static Vector2 getOutPos() {
		return inst.outPos;
	}
	
	public static boolean isCellMoveableFor(int x, int y, int w, int h, Actor actor) {
		w -= 32;
		h -= 32;
		
		int uW = w / 32;
		int uH = h / 32;
		
		TiledMapTileLayer layer = (TiledMapTileLayer)map.getMap().getLayers().get("Ground");
		layer.getCell(x, y);
		for(int i = 0; i <= uW; i++) {
			for(int j = 0; j <= uH; j++) {
				if(layer.getCell(x + i, y + j) == null) return false;
			}
		}
		
		if(map.isUnitInPos(x, y, w, h, actor)) return false;
		else return true;
	}
	
	public static Vector2 getCurrentMapSize() {
		return new Vector2(
				((TiledMapTileLayer)map.getMap().getLayers().get(0)).getWidth(), 
				((TiledMapTileLayer)map.getMap().getLayers().get(0)).getHeight());
	}
	
	public static void addUnit(Actor actor) {
		map.addUnit(actor);
	}
	
	public static void removeUnit(Actor actor) {
		map.removeUnit(actor);
	}
}
