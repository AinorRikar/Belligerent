package com.sl.belligerent.core.world;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class MapManager {
	
	private static MapManager inst = new MapManager();
	
	private static Map map;
	
	private MapManager () {
		
	}
	
	public static MapManager inst() {
		return inst;
	}
	
	public static void setMap(Map map) {
		inst.map = map;
	}
	
	public static boolean isCellMoveable(int x, int y) {
		TiledMapTileLayer layer = (TiledMapTileLayer)map.getMap().getLayers().get("Ground");
		layer.getCell(x, y);
		return layer.getCell(x, y) != null;
	}
	
	public static Vector2 getCurrentMapSize() {
		return new Vector2(
				((TiledMapTileLayer)map.getMap().getLayers().get(0)).getWidth(), 
				((TiledMapTileLayer)map.getMap().getLayers().get(0)).getHeight());
	}
}
